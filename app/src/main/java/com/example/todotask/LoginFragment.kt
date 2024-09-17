package com.example.todotask

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.visibilityOn.setOnClickListener {
            binding.visibilityOn.visibility = View.GONE
            binding.visibilityOff.visibility = View.VISIBLE
            binding.editTextPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.editTextPassword.setSelection(binding.editTextPassword.text.length)
        }
        binding.visibilityOff.setOnClickListener {
            binding.visibilityOff.visibility = View.GONE
            binding.visibilityOn.visibility = View.VISIBLE
            binding.editTextPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.editTextPassword.setSelection(binding.editTextPassword.text.length)
        }

        binding.loginBtn.setOnClickListener {
            val phoneNumber = binding.phoneNumberEt.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (phoneNumber == "01308766666") {
                if (password == "123456") {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToTodoTaskFragmentNavId()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Incorrect Password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Username or Password is not correct...", Toast.LENGTH_SHORT).show()
            }
        }


        binding.phoneNumberEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("Log404", "beforeTextChanged: s=$s, start=$start, count=$count, after=$after")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s != null) {
                    if (s.isEmpty()) {
                        clearPhoneNumberError()
                    } else if (s?.startsWith("01") == true) {
                        clearPhoneNumberError()
                    } else {
                        showPhoneNumberError("Invalid Number")
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("Log404", "afterTextChanged: s=$s")
            }
        })

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("Log404", "beforeTextChanged: s=$s, start=$start, count=$count, after=$after")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! >= 6) {
                    clearPasswordError()
                } else {
                    showPasswordError("Password must be at least 6 characters")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("Log404", "afterTextChanged: s=$s")
                clearErrorMessages()
            }
        })

    }

    private fun clearPasswordError() {
        binding.passErrorTv.text = ""
        binding.passErrorTv.visibility = View.GONE
    }

    private fun clearPhoneNumberError() {
        binding.numberErrorTv.text = ""
        binding.numberErrorTv.visibility = View.GONE
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

