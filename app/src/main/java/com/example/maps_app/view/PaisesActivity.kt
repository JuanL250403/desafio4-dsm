package com.example.maps_app.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio_app.utils.launchRequest
import com.example.maps_app.controller.PaisController
import com.example.maps_app.databinding.ActivityPaisesBinding
import com.example.maps_app.view.adapters.PaisAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaisesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaisesBinding

    private lateinit var paisAdapter: PaisAdapter

    @Inject
    lateinit var countriesController: PaisController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        paisAdapter = PaisAdapter(::verDetalles)

        binding = ActivityPaisesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPaises.adapter = paisAdapter
        binding.rvPaises.layoutManager = LinearLayoutManager(this)

        val region = intent.getStringExtra("region") ?: ""

        launchRequest(binding.cargando.root, binding.error) {
            val paises = countriesController.obtenerPaises(region)

            paisAdapter.actualizarPaises(paises)
        }
    }

    fun verDetalles(codigo: String) {
        val detalle = Intent(this, DetallePais::class.java).apply {
            putExtra("codigo", codigo)
        }

        startActivity(detalle)
    }
}