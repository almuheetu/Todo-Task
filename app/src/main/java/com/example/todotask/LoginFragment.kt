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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarInclude.toolbarTitle.text = "Login"
        binding.toolbarInclude.backButton.visibility = View.GONE

        binding.imageViewEye.setOnClickListener {

        }

        binding.loginBtn.setOnClickListener {
            val phoneNumber = binding.phoneNumberEt.text.toString()
            val password = binding.editTextPassword.text.toString()
            clearErrorMessages()
            if (isValidPhoneNumber(phoneNumber)) {
                if (phoneNumber == "01308766666") {
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


    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 11 && phoneNumber.startsWith("01")
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun showPhoneNumberError(number: String) {
        binding.numberErrorTv.text = number
        binding.numberErrorTv.visibility = View.VISIBLE
    }

    private fun showPasswordError(password: String) {
        binding.passErrorTv.text = password
        binding.passErrorTv.visibility = View.VISIBLE
    }

    private fun clearErrorMessages() {
        binding.numberErrorTv.text = ""
        binding.numberErrorTv.visibility = View.GONE
        binding.passErrorTv.text = ""
        binding.passErrorTv.visibility = View.GONE
    }

}

