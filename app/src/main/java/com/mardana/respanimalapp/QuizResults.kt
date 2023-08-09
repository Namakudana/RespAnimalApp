package com.mardana.respanimalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mardana.respanimalapp.databinding.ActivityQuizResultsBinding
import com.mardana.respanimalapp.user_preference.UserPreference

class QuizResults : AppCompatActivity() {
    private lateinit var binding: ActivityQuizResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPreference = UserPreference(this)
        with(binding) {
            if (userPreference.getUserData().score != null) {
                title.text = " Anda sudah menyelesaikan quiz"
                detail.text = "Nilai anda adalah ${userPreference.getUserData().score}"
                startQuizButton.visibility = View.GONE
            } else {
                startQuizButton.setOnClickListener {
                    startActivity(Intent(this@QuizResults, PracticePageActivity::class.java))
                }
            }
            binding.backBtn.setOnClickListener {
                onBackPressed()
            }
        }
    }
}