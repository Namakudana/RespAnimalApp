package com.mardana.respanimalapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.mardana.respanimalapp.databinding.ActivityMainBinding
import kotlin.system.exitProcess

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
            startActivity(Intent(this, Info::class.java))
        }

        binding.btnKD.setOnClickListener {
            startActivity(Intent(this, BasicCompetencies::class.java))
        }

        binding.btnMateri.setOnClickListener {
            startActivity(Intent(this, ViewMaterial::class.java))
        }

        binding.btnQuiz.setOnClickListener {
            startActivity(Intent(this, PracticePage::class.java))
        }

        binding.btnNilai.setOnClickListener {
            startActivity(Intent(this, ViewScoreList::class.java))
        }

    }

    private fun showExitConfirmationDialog(){
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Keluar")
            .setMessage("Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                exitProcess(0)
            }
            .setNegativeButton("No", null)
            .create()
        alertDialog.show()
    }


}