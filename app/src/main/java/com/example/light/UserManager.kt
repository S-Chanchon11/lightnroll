package com.example.light

import com.example.light.profile.ProfileModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

object UserManager {
    private var uid: String? = null
    private var auth: FirebaseAuth = Firebase.auth
    private var user_level: Int = 0
    private var rid: String? = ""
    private var isLogin: Boolean = false

//    private var data_model =  ProfileModel()
    fun setLoginStatus(isUserLogin: Boolean): Boolean {
        isLogin = isUserLogin
        return isLogin
    }
    fun loginCheck(): Boolean {
        return isLogin
    }
    fun setUserInfo(info: ProfileModel) {
        uid = info.uid
        user_level = info.user_level
    }
    fun setUserLevel(ulvl: Int): Int {
        user_level = ulvl
        return user_level
    }
    fun getUserLevel(): Int {
        return user_level
    }

    fun setRid(): String {
        val allowedChars = ('a'..'z') + ('0'..'9')
        rid = (1..5)
            .map { allowedChars.random() }
            .joinToString("")
        return rid as String
    }
    fun clearRid(): String {
        rid = ""
        return rid as String
    }

    fun getRid(): String? {
        return rid
    }

    fun getUid(): String? {
        if (uid == null) {
            uid = FirebaseAuth.getInstance().currentUser?.uid
//            uid?.let { Log.d("UM", it) }
        }
        return uid
    }

    fun setUid(newUid: String) {
        uid = newUid
    }
}
