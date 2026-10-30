package com.example.maps_app.view

import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ejercicio_app.utils.launchRequest
import com.example.maps_app.BuildConfig
import com.example.maps_app.R
import com.example.maps_app.controller.CiudadesController
import com.example.maps_app.controller.PaisController
import com.example.maps_app.databinding.ActivityDetallePaisBinding
import com.example.maps_app.model.ui.CiudadUiModel
import com.example.maps_app.model.ui.PaisDetalleUiModel
import com.example.maps_app.view.adapters.CiudadAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.text.NumberFormat
import java.util.Locale
import java.util.prefs.Preferences
import javax.inject.Inject

@AndroidEntryPoint
class DetallePais : AppCompatActivity() {

    @Inject
    lateinit var paisController: PaisController

    private val LAT_MAX = 85.0

    @Inject
    lateinit var ciudadesController: CiudadesController

    private lateinit var ciudadAdapter: CiudadAdapter

    private var pais: PaisDetalleUiModel? = null

    private var ciudades: List<CiudadUiModel>? = null

    private lateinit var binding: ActivityDetallePaisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Configuration.getInstance().load(
            applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

        Configuration.getInstance().userAgentValue =
            "JuanMapasEjercicio/${BuildConfig.VERSION_NAME} (juaeivamont@gmail.com)"

        Configuration.getInstance().osmdroidTileCache.deleteRecursively()


        val codigo = intent.getStringExtra("codigo") ?: ""

        binding = ActivityDetallePaisBinding.inflate(layoutInflater)

        ciudadAdapter = CiudadAdapter(::enfocarCiudad)

        binding.rvCiudades.adapter = ciudadAdapter
        binding.rvCiudades.layoutManager = LinearLayoutManager(this)

        android.util.Log.d("OSM", "UA = ${Configuration.getInstance().userAgentValue}")

        setContentView(binding.root)


        configurarMapa()

        launchRequest(binding.cargando.root, binding.error) {
            pais = paisController.obtenerPais(codigo)

            ciudades = ciudadesController.obtenerCiudades(codigo)

            ciudadAdapter.actualizarCiudades(ciudades ?: listOf())

            cargarDatosPais()
            centrarMapa()
            ubicarCiudades()
        }

    }

    private fun configurarMapa() {
        binding.mvCiudades.setMultiTouchControls(true)
        binding.mvCiudades.setTileSource(TileSourceFactory.OpenTopo)
    }

    private fun ubicarCiudades() {

        binding.mvCiudades.overlays.clear()

        ciudades?.forEach { c ->
            val latitud = c.latitud?.toDouble() ?: return@forEach
            val longitud = c.longitud?.toDouble() ?: return@forEach

            val marker = Marker(binding.mvCiudades).apply {
                position = GeoPoint(latitud, longitud)
                title = c.nombre
                snippet = armarDatosCiudadMarker(c)
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            }
            binding.mvCiudades.overlays.add(marker)
        }

        binding.mvCiudades.invalidate()
    }

    private fun armarDatosCiudadMarker(c: CiudadUiModel): String {
        val lineas = mutableListOf<String>()

        if (c.esCapital) lineas.add("★ Capital")

        c.clima?.temperatura?.let { lineas.add("Temperatura: ${it.toInt()}°C") }
        c.clima?.humedad?.let { lineas.add("Humedad: $it%") }

        return lineas.joinToString("\n")
    }

    private fun centrarMapa() {
        val lat = pais?.latitud?.toDouble()
        val lng = pais?.longitud?.toDouble()


        if (lat == null || lng == null ||
            lat !in -90.0..90.0 || lng !in -180.0..180.0 ||
            (lat == 0.0 && lng == 0.0)) {
            binding.mvCiudades.controller.setZoom(2.0)
            binding.mvCiudades.controller.setCenter(GeoPoint(20.0, 0.0))
            binding.tvAvisoMapa.text = "Ubicación inaccesible"
            binding.tvAvisoMapa.isVisible = true
            return
        }

        val latSegura = lat.coerceIn(-LAT_MAX, LAT_MAX)
        val zoom = if (Math.abs(lat) > 60) 3.0 else 5.0

        if (ciudades.isNullOrEmpty()) {
            binding.tvAvisoMapa.text = "Sin ciudades registradas"
            binding.tvAvisoMapa.isVisible = true
            binding.rvCiudades.isVisible = false
            binding.tvCiudades.isVisible = false
        }

        binding.mvCiudades.controller.setZoom(zoom)
        binding.mvCiudades.controller.setCenter(GeoPoint(latSegura, lng))
    }

    private fun cargarDatosPais() {
        val p = pais ?: return
        val vacio = "—"

        binding.tvNombre.text = p.nombre ?: vacio

        binding.tvPoblacion.text = p.poblacion?.let {
            NumberFormat.getIntegerInstance(Locale.getDefault()).format(it)
        } ?: vacio

        binding.tvCapital.text = p.capital?.takeIf { it.isNotBlank() } ?: vacio
        binding.tvRegion.text = p.region?.takeIf { it.isNotBlank() } ?: vacio
        binding.tvSubRegion.text = p.subRegion?.takeIf { it.isNotBlank() } ?: vacio
        binding.tvCodigosISO.text = p.codigosISO?.takeIf { it.isNotBlank() } ?: vacio


        binding.tvIdiomas.text = p.idiomas
            ?.filter { it.isNotBlank() }
            ?.joinToString(", ")
            ?.takeIf { it.isNotEmpty() } ?: vacio


        binding.tvMonedas.text = p.monedas
            ?.filter { it.isNotBlank() }
            ?.joinToString(", ")
            ?.takeIf { it.isNotEmpty() } ?: vacio


        val lat = p.latitud?.toDouble()
        val lng = p.longitud?.toDouble()

        Glide.with(this)
            .load(pais?.bandera)
            .placeholder(R.drawable.ic_cargando)
            .error(R.drawable.ic_error)
            .into(binding.ivBandera)

        binding.tvCoordenadas.text = if (lat != null && lng != null) {
            val ns = if (lat >= 0) "N" else "S"
            val eo = if (lng >= 0) "E" else "O"
            String.format(Locale.US, "%.4f° %s, %.4f° %s", Math.abs(lat), ns, Math.abs(lng), eo)
        } else {
            vacio
        }
    }

    private fun enfocarCiudad(latitud: Double, longitud: Double) {
        val punto = GeoPoint(latitud, longitud)
        binding.mvCiudades.controller.animateTo(punto, 12.0, 1000L)

        val rect = android.graphics.Rect()
        binding.mvCiudades.getDrawingRect(rect)
        binding.scroll.offsetDescendantRectToMyCoords(binding.mvCiudades, rect)
        binding.scroll.smoothScrollTo(0, rect.top)
    }

    override fun onResume() {
        super.onResume()
        binding.mvCiudades.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mvCiudades.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mvCiudades.onDetach()
    }
}