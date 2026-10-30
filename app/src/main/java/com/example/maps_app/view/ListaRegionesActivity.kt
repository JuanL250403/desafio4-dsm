package com.example.maps_app.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maps_app.R
import com.example.maps_app.databinding.ActivityListaRegionesBinding
import com.example.maps_app.view.adapters.RegionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListaRegionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaRegionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListaRegionesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.rvRegiones.adapter = RegionAdapter(::verPaises)
        binding.rvRegiones.layoutManager = LinearLayoutManager(this)
    }

    private fun verPaises(region: String) {
        val paises = Intent(this, PaisesActivity::class.java).apply {
            putExtra("region", region)
        }

        startActivity(paises)
    }
}