package com.example.ejercicio_app.utils

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.maps_app.databinding.ErrorBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Lanza una operación de red ligada al ciclo de vida de la Activity,
// muestra el ProgressBar y convierte los errores en mensajes para el usuario
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlinx.coroutines.CancellationException

fun AppCompatActivity.launchRequest(
    progress: View,
    error: ErrorBinding? = null,
    block: suspend () -> Unit
) {

    error?.root?.isVisible = false

    fun fallo(mensaje: String) {
        if (error != null) {
            error.tvErrorMensaje.text = mensaje
            error.root.isVisible = true
            error.btnReintentar.setOnClickListener {
                launchRequest(progress, error, block)
            }
        } else {
            Toast.makeText(applicationContext, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    lifecycleScope.launch {
        progress.isVisible = true
        try {
            block()
        } catch (e: CancellationException) {
            throw e
        } catch (e: IllegalArgumentException) {
            fallo(e.message ?: "Datos inválidos")
        } catch (e: HttpException) {
            fallo("Error del servidor: HTTP ${e.code()}")
        } catch (e: UnknownHostException) {
            fallo("Sin conexión a internet. Revisa tu red e inténtalo de nuevo.")
        } catch (e: SocketTimeoutException) {
            fallo("La conexión tardó demasiado. Inténtalo de nuevo.")
        } catch (e: ConnectException) {
            fallo("No se pudo conectar con el servidor.")
        } catch (e: IOException) {
            fallo("Error de conexión. Inténtalo de nuevo.")
        } finally {
            progress.isVisible = false
        }
    }
}