package com.example.maps_app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maps_app.databinding.RegionItemBinding
import com.example.maps_app.model.ui.Region

class RegionAdapter (
    private val verPaises: (String) -> Unit
) : RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {
    class RegionViewHolder(val binding: RegionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var regiones = listOf(
        Region(nombre = "África", valor = "Africa"),
        Region(nombre = "América", valor = "Americas"),
        Region(nombre = "Asia", valor = "Asia"),
        Region(nombre = "Europa", valor = "Europe"),
        Region(nombre = "Oceanía", valor = "Oceania"),
        Region(nombre = "Antártida", valor = "Antarctic")
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegionViewHolder {
        val binding =
            RegionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RegionViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        val b = holder.binding

        b.tvRegion.text = regiones[position].nombre
        b.root.setOnClickListener { verPaises(regiones[position].valor) }
    }

    override fun getItemCount(): Int = regiones.size
}