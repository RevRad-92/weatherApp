package com.example.weatherapp.presentacion.clima.forecast

import com.example.weatherapp.repository.models.ListForecast

sealed class PronosticoEstado{
    data class Exitoso(
        val climas: List<ListForecast>
    ) : PronosticoEstado()
    data class Error(
        val mensaje: String = "",
    )     : PronosticoEstado()
    data object Vacio: PronosticoEstado()
    data object Cargando: PronosticoEstado()

}