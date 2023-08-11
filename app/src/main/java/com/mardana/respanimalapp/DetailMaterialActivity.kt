package com.mardana.respanimalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.mardana.respanimalapp.data.MaterialModel
import com.mardana.respanimalapp.databinding.ActivityDetailMaterialBinding

class DetailMaterialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMaterialBinding
    object IntentId {
        const val materialExtra = "MATERIAL_EXTRA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


        val intent = intent
        val getMaterial = intent.getParcelableExtra<MaterialModel>(IntentId.materialExtra)
        if (getMaterial != null) {
            setupData(getMaterial)
        }
    }

    private fun setupData(material: MaterialModel) {
        Glide.with(this)
            .load(material.picture?:"")
            .into(binding.avatar)
        binding.judul.text = material.title ?: ""
        binding.ringkasan.text = material.summary ?: ""
        binding.detail.text = material.detail ?: ""
    }
}