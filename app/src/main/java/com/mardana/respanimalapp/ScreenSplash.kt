package com.mardana.respanimalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mardana.respanimalapp.databinding.ActivityScreenSplashBinding
import com.mardana.respanimalapp.user_preference.UserPreference

class ScreenSplash : AppCompatActivity() {
    private lateinit var binding: ActivityScreenSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashScreenDuration = 2000L
        val userData = UserPreference(this).getUserData()
        binding.root.postDelayed({
            if (userData.uid != null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, splashScreenDuration)
    }
}