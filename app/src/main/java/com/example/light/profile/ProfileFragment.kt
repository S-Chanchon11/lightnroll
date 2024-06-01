package com.example.light.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.light.R
import com.example.light.login.viewmodel.LoginViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var usernameTxt: TextView
    private lateinit var dobTxt: TextView
    private lateinit var authViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        usernameTxt = view.findViewById(R.id.username)
        dobTxt = view.findViewById(R.id.dob)
        authViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        authViewModel.userData.observe(
            viewLifecycleOwner,
            Observer { userModel ->
                userModel?.let {
                    // Use the user data (e.g., display it in the UI)
                    val email = it["email"]
                    val username = it["username"]
//                    Toast.makeText(context,username.toString(),Toast.LENGTH_SHORT).show()
                    usernameTxt.text = username.toString()
                    // Update UI with user data
                } ?: run {
                    // Handle the case where user data is null (e.g., show an error message)
                }
            }
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
