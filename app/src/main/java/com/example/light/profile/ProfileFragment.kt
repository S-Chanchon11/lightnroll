package com.example.light.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.light.R
import com.example.light.login.viewmodel.LoginViewModel
import com.example.light.record.SettingFragment
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class ProfileFragment : Fragment() {
    val TAG = "Profile Fragment"
    private lateinit var viewModel: ProfileViewModel
    private lateinit var usernameTxt: TextView
    private lateinit var dobTxt: TextView
    private lateinit var authViewModel: LoginViewModel
    private lateinit var profileImage: ImageView
    private lateinit var settingBtn: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        usernameTxt = view.findViewById(R.id.username)
        dobTxt = view.findViewById(R.id.dob)
        profileImage = view.findViewById(R.id.profile_pic)
        settingBtn = view.findViewById(R.id.settingButton)
        authViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        authViewModel.userData.observe(
            viewLifecycleOwner,
            Observer { userModel ->
                userModel?.let {
                    val email = it["email"]
                    val username = it["username"]
                    usernameTxt.text = username.toString()

                    loadProfilePicture(it["_uid"].toString(), profileImage)
                } ?: run {
                }
            }
        )
        fetchProfile("DSPlOrYFN6d2e00lOlERTh3riTj1")
        fetchResult("DSPlOrYFN6d2e00lOlERTh3riTj1")
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settingFragment = SettingFragment()

        settingBtn.setOnClickListener {
            replaceFragment(settingFragment)
        }
    }

    private fun fetchProfile(userId: String) {
        viewModel.getData(userId)?.observe(
            viewLifecycleOwner,
            Observer { profileModel ->
                Log.d(TAG, profileModel.email)
            }
        )
    }
    private fun fetchResult(userId: String) {
        viewModel.getResultData(userId)?.observe(
            viewLifecycleOwner,
            Observer { evalutemodel ->
                Log.d(TAG, evalutemodel[0].score.toString())
            }
        )
    }
    private fun loadProfilePicture(userId: String, imageView: ImageView) {
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.reference.child("/users/$userId/profile.jpeg")
        try {
            // Create a temporary file
            val localFile = File.createTempFile("profile_picture", "jpg")

            storageReference.getFile(localFile).addOnSuccessListener {
                // The file has been downloaded successfully
                val bitmap: Bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                imageView.setImageBitmap(bitmap)
            }.addOnFailureListener { exception ->
                // Handle any errors
                Log.e("FirebaseStorage", "Error downloading profile picture", exception)
            }
        } catch (e: Exception) {
            Log.e("FirebaseStorage", "Error creating temporary file", e)
        }
    }
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
