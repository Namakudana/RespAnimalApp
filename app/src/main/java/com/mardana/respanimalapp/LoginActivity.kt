package com.mardana.respanimalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mardana.respanimalapp.databinding.ActivityLoginBinding
import com.mardana.respanimalapp.user_preference.UserPreference

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPreference = UserPreference(this)

        binding.btnLogin.setOnClickListener {
            userPreference.loginUser(
                name = binding.tfName.text.toString(),
                room = binding.tfRoom.text.toString()
            )
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}