package com.example.todotask

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todotask.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }


    private fun showPhoneNumberError(number: String) {
        binding.numberErrorTv.text = number
        binding.numberErrorTv.visibility = View.VISIBLE
    }

    private fun showPasswordError(password: String) {
        binding.passErrorTv.text = password
        binding.passErrorTv.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarInclude.toolbarTitle.text = "Login"
        binding.toolbarInclude.backButton.visibility = View.GONE
        binding.imageViewEye.setOnClickListener {
            togglePasswordVisibility()
        }
        binding.loginBtn.setOnClickListener {
            val phoneNumber = binding.phoneNumberEt.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (isValidPhoneNumber(phoneNumber)) {
                if (phoneNumber == "01789803342") {
                    if (isValidPassword(password)) {
                        if (password == "123456") {
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToTodoTaskFragmentNavId()
                            findNavController().navigate(action)
                        } else {
                            showPasswordError("Invalid Password")
                        }
                    } else {
                        showPasswordError("Password must be at least 6 characters")
                    }
                } else {
                    showPhoneNumberError("Invalid Number")
                }
            } else {
                showPhoneNumberError("Phone number must be 11 digits and start with '01'")
            }
        }
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 11 && phoneNumber.startsWith("01")
    }

    private fun togglePasswordVisibility() {
        val isPasswordVisible = binding.editTextPassword.inputType != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

        if (isPasswordVisible) {
            binding.editTextPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24) // Open eye icon
        } else {
            binding.editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.imageViewEye.setImageResource(R.drawable.baseline_remove_red_eye_24) // Closed eye icon
        }
        binding.editTextPassword.setSelection(binding.editTextPassword.text.length) // Fix cursor position
    }

}

