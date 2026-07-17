package com.example.habittracker.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentLoginBinding
import com.example.habittracker.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = requireContext().getSharedPreferences(
            "login_session",
            Context.MODE_PRIVATE
        )

        val isLoggedIn = preferences.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            findNavController().navigate(
                R.id.actionDashboardFragment
            )

            return
        }

        viewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        observeViewModel()

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()

            val password = binding.txtPassword.text.toString()

            if (
                username.isBlank() ||
                password.isBlank()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Username dan password harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.login(
                    username,
                    password
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(viewLifecycleOwner) { isLoginSuccessful ->

            if (isLoginSuccessful) {
                val preferences = requireContext().getSharedPreferences(
                    "login_session",
                    Context.MODE_PRIVATE
                )

                preferences.edit()
                    .putBoolean("is_logged_in", true)
                    .apply()

                Toast.makeText(
                    requireContext(),
                    "Login berhasil",
                    Toast.LENGTH_SHORT
                ).show()

                findNavController().navigate(
                    R.id.actionDashboardFragment
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Username atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}