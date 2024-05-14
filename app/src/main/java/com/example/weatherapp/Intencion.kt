package com.example.weatherapp

sealed class Intencion {
    object BorrarTodo: Intencion()
    object MostrarCordoba: Intencion()
    object MostrarCABA: Intencion()
    object MostrarError: Intencion()
}