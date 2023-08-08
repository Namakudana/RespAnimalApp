package com.mardana.respanimalapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.mardana.respanimalapp.databinding.ActivityPracticePageBinding

class PracticePage : AppCompatActivity() {
    private lateinit var binding: ActivityPracticePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.startQuizBtn.setOnClickListener {

        }
    }

}