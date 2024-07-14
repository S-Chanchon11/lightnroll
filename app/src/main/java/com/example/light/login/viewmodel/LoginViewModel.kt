package com.example.light.login.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.UserManager
import com.example.light.profile.ProfileModel
import com.example.light.profile.ProfileRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LoginViewModel : ViewModel() {
    val TAG = "LoginViewModel"
    private val _user = MutableLiveData<FirebaseUser?>()
    private var auth: FirebaseAuth
    private val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance().reference
    val user: LiveData<FirebaseUser?> get() = _user

    private val _userData = MutableLiveData<Map<String, Any>?>()
    val userData: MutableLiveData<Map<String, Any>?> get() = _userData

    private val profileRepository = ProfileRepository()

    private val _userDetails = MutableLiveData<ProfileModel>()
    val userDetails: LiveData<ProfileModel> get() = _userDetails

    private var _profileData = MutableLiveData<ProfileModel>()
    val profiledata: MutableLiveData<ProfileModel> get() = _profileData
    init {
        auth = Firebase.auth
        _user.value = auth.currentUser
        if (_user.value != null) {
            UserManager.setUserLevel(2)
        } else {
            UserManager.setUserLevel(0)
        }
        fetchUserData()
    }
    fun signUp(email: String, password: String, userData: HashMap<String, String>) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    // DSPlOrYFN6d2e00lOlERTh3riTj1
                    db.collection("users").document(_user.value!!.uid).set(userData)
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot successfully written!")
                            observeData(auth.currentUser!!.uid)
                        }
                        .addOnFailureListener {
                        }
                } else {
                }
            }
    }

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                } else {
                    _user.value = null
                }
            }
    }
    fun signOut() {
        UserManager.setLoginStatus(false)
        auth.signOut()
        _user.value = null
    }
    val currentUserId: Flow<User?>
        @SuppressLint("RestrictedApi")
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid) })
            }
            Firebase.auth.addAuthStateListener(listener)
            awaitClose { Firebase.auth.removeAuthStateListener(listener) }
        }

    private fun fetchUserData() {
        val user = auth.currentUser
        user?.let {
            val uid = it.uid
            UserManager.setUid(uid)
//
            db.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        _userData.value = document.data

//                        Log.d("Fetching User data", _userData.value.toString())
                    } else {
                        _userData.value = null
                    }
                }
                .addOnFailureListener {
                    _userData.value = null
                }
        }
    }
    fun observeData(userId: String): LiveData<ProfileModel>? {
        _profileData = profileRepository.getServicesApiCall(userId)
        return _profileData
    }

    fun fetchUserDetails() {
//        viewModelScope.launch {
//            val data = _userDetails.postValue(profileRepository.getUserDetails().value)
//        }
    }
}
