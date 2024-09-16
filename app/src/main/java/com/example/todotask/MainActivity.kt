package com.example.todotask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todotask.databinding.ActivityMainBinding
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(
        savedInstanceState: Bundle?,
    ) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = findViewById<TextView>(R.id.sing_in_tv)
        val fullText = "Don't have an account? Sign in"
        val spannable = SpannableString(fullText)
        val start = fullText.indexOf("Sign in")
        val end = start + "Sign in".length
        spannable.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannable

    }
}


