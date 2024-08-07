package com.example.light.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.light.MainActivity
import com.example.light.R
import com.example.light.UserManager
import com.example.light.login.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    val TAG = "LoginFragment"
    private lateinit var signinBtn: Button
    private lateinit var signupBtn: TextView
    private lateinit var guestMode: TextView
    private lateinit var emailTxt: EditText
    private lateinit var passwordTxt: EditText
    private lateinit var viewModel: LoginViewModel
    private var status: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        signinBtn = view.findViewById(R.id.signin_button)
        signupBtn = view.findViewById(R.id.signup_button)
        guestMode = view.findViewById(R.id.guestmode_button)
        emailTxt = view.findViewById(R.id.email_and_username_box)
        passwordTxt = view.findViewById(R.id.password_box)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signupFragment = SignupFragment()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        observeData()

        signinBtn.setOnClickListener {
            if (emailTxt.text.toString().isEmpty() && passwordTxt.text.toString().isEmpty()) {
                emailTxt.background = resources.getDrawable(R.drawable.empty_input_box)
                emailTxt.setPadding(42)
                passwordTxt.background = resources.getDrawable(R.drawable.empty_input_box)
                passwordTxt.setPadding(42)
            } else if (emailTxt.text.toString().isNotEmpty() && passwordTxt.text.toString().isNotEmpty()) {
                viewModel.signIn(emailTxt.text.toString(), passwordTxt.text.toString())
                observeData()
            }
        }

        signupBtn.setOnClickListener {
            replaceFragment(signupFragment)
        }
        guestMode.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun observeUserProfile() {
        try {
            viewModel.profiledata.observe(
                viewLifecycleOwner,
                Observer { user ->
                    if (user != null) {
                        UserManager.setUserInfo(user)
                    } else {
                    }
                }
            )
        } catch (e: Exception) {
            print(e)
        }
    }

    private fun observeData() {
        try {
            viewModel.user.observe(
                viewLifecycleOwner,
                Observer { user ->
                    if (user != null) {
                        status = 2

                        UserManager.setUserLevel(2)
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        status = 1
                        Log.d("LoginVM", "not login")
                        UserManager.setUserLevel(0)
                    }
                }
            )
        } catch (e: Exception) {
            print(e)
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
