package com.mardana.respanimalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.mardana.respanimalapp.databinding.ActivityMainBinding
import com.mardana.respanimalapp.user_preference.UserPreference

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            showExitConfirmationDialog()
        }

        binding.btnInfo.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.btnKD.setOnClickListener {
            startActivity(Intent(this, BasicCompetencies::class.java))
        }

        binding.btnMateri.setOnClickListener {
            startActivity(Intent(this, ViewMaterial::class.java))
        }

        binding.btnQuiz.setOnClickListener {
            startActivity(Intent(this, PracticePageActivity::class.java))
        }

        binding.btnNilai.setOnClickListener {
            startActivity(Intent(this, QuizResults::class.java))
        }

    }

    private fun showExitConfirmationDialog(){
        val userPracticePage = UserPreference(this)
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Keluar")
            .setMessage("Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                userPracticePage.logoutUser()
            }
            .setNegativeButton("No", null)
            .create()
        alertDialog.show()
    }
}