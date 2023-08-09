package com.mardana.respanimalapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mardana.respanimalapp.databinding.ActivityBasicCompetenciesBinding

class BasicCompetencies : AppCompatActivity() {
    private lateinit var binding: ActivityBasicCompetenciesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicCompetenciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

}