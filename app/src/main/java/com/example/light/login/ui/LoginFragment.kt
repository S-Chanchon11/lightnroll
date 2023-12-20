package com.example.light.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.light.MainActivity
import com.example.light.R

class LoginFragment : Fragment() {

    private lateinit var signinBtn: Button
    private lateinit var signupBtn: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        signinBtn = view.findViewById(R.id.signin_button)
        signupBtn = view.findViewById(R.id.signup_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signinBtn.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        signupBtn.setOnClickListener {
//            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.login_frame_layout, LoginFragment())
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
        }
    }
}
