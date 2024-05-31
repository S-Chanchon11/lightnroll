package com.example.light.login.viewmodel

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LoginViewModel : ViewModel() {
    private val _user = MutableLiveData<FirebaseUser?>()
    private var auth: FirebaseAuth
    private val db = Firebase.firestore
    val user: LiveData<FirebaseUser?> get() = _user

    init {
        auth = Firebase.auth
        _user.value = auth.currentUser
    }
    fun signUp(email: String, password: String, userData:HashMap<String,String>) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    Log.d("LoginVM signup",FirebaseAuth.getInstance().currentUser!!.uid)
                    Log.d("LoginVM signup",auth.currentUser!!.uid)
                    //DSPlOrYFN6d2e00lOlERTh3riTj1
                    db.collection("users").document(_user.value!!.uid).set(userData)
                        .addOnSuccessListener {
                            // User data successfully written
                            Log.d(TAG, "DocumentSnapshot successfully written!")
                        }
                        .addOnFailureListener { e ->
                            // Handle the error
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
//                    firestore.collection("users").document(currentUserId).get()
                    fetchUserData()
                } else {
                    _user.value = null
                }
            }
    }
    fun signOut() {
        auth.signOut()
        _user.value = null
    }
    val currentUserId : Flow<User?>
        @SuppressLint("RestrictedApi")
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid) })
            }
            Firebase.auth.addAuthStateListener(listener)
            awaitClose {Firebase.auth.removeAuthStateListener(listener)}
        }

    fun fetchUserData(): LiveData<Map<String, Any>?> {
//        val user = FirebaseAuth.getInstance().currentUser
        val userData = MutableLiveData<Map<String, Any>?>()
        Log.d("LoginVM",currentUserId)
        user?.let {
            db.collection("users").document(_user.value!!.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        userData.value = document.data
                    } else {
                        userData.value = null
                    }
                }
                .addOnFailureListener { e ->
                    userData.value = null
                }
        }
        return userData
    }

}
