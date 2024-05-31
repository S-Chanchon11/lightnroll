package com.example.light.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.light.MainActivity
import com.example.light.R
import com.example.light.login.viewmodel.LoginViewModel

class SignupFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var emailTxt: EditText
    private lateinit var passwordTxt: EditText
    private lateinit var repasswordTxt: EditText
    private lateinit var signUpBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        // Inflate the layout for this fragment
        emailTxt = view.findViewById(R.id.emailbox)
        passwordTxt = view.findViewById(R.id.password_box)
        repasswordTxt = view.findViewById(R.id.re_enter_password_box)
        signUpBtn = view.findViewById(R.id.signup_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        signUpBtn.setOnClickListener {
            if (emailTxt.text.toString().isEmpty() && passwordTxt.text.toString().isEmpty()) {
                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
            } else {
                val userData = hashMapOf(
                    "email" to emailTxt.text.toString()
                )
                viewModel.signUp(
                    emailTxt.text.toString(),
                    passwordTxt.text.toString(),
                    userData
                )
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }

//
        }
    }
}
