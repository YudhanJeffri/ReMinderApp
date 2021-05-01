package com.reminder.reminderapp.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.reminder.reminderapp.R
import com.reminder.reminderapp.ui.activity.MainActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}