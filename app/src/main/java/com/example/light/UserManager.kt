package com.example.light

import android.util.Log
import com.example.light.profile.ProfileModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

object UserManager {
    private var uid: String? = null
    private var auth: FirebaseAuth = Firebase.auth
    private var user_level : String? = null
//    private var data_model =  ProfileModel()

    fun setUserInfo(data:Map<String,Any>){

    }


    fun getUid(): String? {
        if (uid == null) {
            uid = FirebaseAuth.getInstance().currentUser?.uid
            uid?.let { Log.d("UM", it) }
        }
        return uid
    }

    fun setUid(newUid: String) {
        uid = newUid
    }

}
