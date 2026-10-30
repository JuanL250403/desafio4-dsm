package com.example.maps_app.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maps_app.R
import com.example.maps_app.databinding.PaisItemBinding
import com.example.maps_app.model.network.CountryResponse
import com.example.maps_app.model.ui.PaisUiModel

class PaisAdapter (
    private val verDetalles: (String) -> Unit
) : RecyclerView.Adapter<PaisAdapter.PaisViewHolder>() {
    class PaisViewHolder(val binding: PaisItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var paises: List<PaisUiModel> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaisViewHolder {
        val binding =
            PaisItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PaisViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val pais = paises[position]
        val b = holder.binding
        val ctx = b.root.context

        b.tvPais.text = pais.nombre

        b.tvCapital.text = if (!pais.capital.isNullOrBlank()) {
            "Capital: ${pais.capital}"
        } else {
            "Sin capital"
        }

        // Bandera
        Glide.with(ctx)
            .load(pais.bandera)
            .placeholder(R.drawable.ic_cargando)
            .error(R.drawable.ic_error)
            .into(b.ivBandera)

        // Clic
        b.root.setOnClickListener {
            val codigo = pais.codigo
            if (!codigo.isNullOrBlank()) {
                verDetalles(codigo)
            }
        }
    }

    fun actualizarPaises(paises: List<PaisUiModel>) {
        this.paises = paises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = paises.size
}