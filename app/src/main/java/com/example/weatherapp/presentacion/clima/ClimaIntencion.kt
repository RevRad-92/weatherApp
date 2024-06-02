package com.example.weatherapp.presentacion.clima

sealed class ClimaIntencion {
    object BorrarTodo: ClimaIntencion()
    object MostrarCordoba: ClimaIntencion()
    object MostrarCABA: ClimaIntencion()
    object MostrarError: ClimaIntencion()
}