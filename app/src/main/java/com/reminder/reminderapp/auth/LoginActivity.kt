package com.reminder.reminderapp.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reminder.reminderapp.R
import com.reminder.reminderapp.databinding.ActivityLoginBinding
import com.reminder.reminderapp.ui.activity.MainActivity


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
    }

    fun login(view: View) {
        if (binding.username.text.toString() == "" || binding.password.text.toString() == ""){
            Toast.makeText(this, "Harap Masukkan Email dan Password", Toast.LENGTH_SHORT).show()
        } else if (binding.username.text.toString() != "admin" || binding.password.text.toString() != "admin"){
            Toast.makeText(this, "Email atau Password Salah", Toast.LENGTH_SHORT).show()
        } else if (binding.username.text.toString() == "admin" || binding.password.text.toString() == "admin") {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}