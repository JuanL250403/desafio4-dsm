package com.example.maps_app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maps_app.R
import com.example.maps_app.databinding.CiudadItemBinding
import com.example.maps_app.model.ui.CiudadUiModel
import java.text.NumberFormat
import java.util.Locale

class CiudadAdapter (
    val enfocarCiudad: (Double, Double) -> Unit
) : RecyclerView.Adapter<CiudadAdapter.CiudadViewHolder>() {
    class CiudadViewHolder(val binding: CiudadItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var ciudades: List<CiudadUiModel> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CiudadViewHolder {
        val binding =
            CiudadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CiudadViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CiudadViewHolder, position: Int) {
        val ciudad = ciudades[position]
        val b = holder.binding
        val ctx = b.root.context

        b.tvNombre.text = ciudad.nombre

        b.tvPoblacion.text = ciudad.poblacion?.let {
            val numero = NumberFormat.getIntegerInstance(Locale.getDefault()).format(it)
            "$numero habitantes"
        } ?: "Población no disponible"

        b.tvTemperatura.text = ciudad.clima?.temperatura?.let {
            "${it.toInt()}°C"
        } ?: "--"


        b.tvHumedad.text = ciudad.clima?.humedad?.let {
            "Humedad $it%"
        } ?: "Humedad --"


        val icono = ciudad.clima?.condicionImg
        if (!icono.isNullOrBlank()) {
            val url = if (icono.startsWith("//")) "https:$icono" else icono
            Glide.with(ctx)
                .load(url)
                .placeholder(R.drawable.ic_cargando)
                .error(R.drawable.ic_error)
                .into(b.ivClima)
        } else {
            b.ivClima.setImageDrawable(null)
        }

        if(ciudades[position].esCapital) {
            b.root.strokeColor = ContextCompat.getColor(b.root.context, R.color.resaltado)
        } else {
            b.root.strokeColor = ContextCompat.getColor(b.root.context, R.color.black)
        }

        b.root.setOnClickListener { enfocarCiudad(ciudades[position].latitud?.toDoubleOrNull() ?: 0.0, ciudades[position].longitud?.toDoubleOrNull() ?: 0.0) }
    }

    fun actualizarCiudades(ciudades: List<CiudadUiModel>) {
        this.ciudades = ciudades
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ciudades.size
}