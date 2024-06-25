package com.example.light.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.light.R
import com.example.light.login.ui.LoginActivity
import com.example.light.login.viewmodel.LoginViewModel

class SettingFragment : Fragment() {
    private lateinit var signout: TextView
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        signout = view.findViewById(R.id.logoutText)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        signout.setOnClickListener {
            viewModel.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
