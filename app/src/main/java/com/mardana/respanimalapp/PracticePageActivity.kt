package com.mardana.respanimalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.mardana.respanimalapp.databinding.ActivityPracticePageBinding
import com.mardana.respanimalapp.question_page.QuestionPageActivity
import com.mardana.respanimalapp.user_preference.UserPreference

class PracticePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticePageBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        setupState()
    }

    private fun setupState() {
        val userPreference = UserPreference(this)
        if (userPreference.getUserData().score != null) {
            with(binding) {
                chooseTopic.text = "Anda sudah mengerjakan quiz ini"
                startQuizBtn.text = "LIHAT NILAI"
            }
        }

        binding.startQuizBtn.setOnClickListener {
            Log.e("","SS")
            if (userPreference.getUserData().score != null) {
                startActivity(Intent(this@PracticePageActivity, QuizResults::class.java))
            } else {
                startActivity(Intent(this@PracticePageActivity, QuestionPageActivity::class.java))
            }
        }
    }
}