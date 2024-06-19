package com.example.weatherapp.router

interface Router{
    fun navegar(route: Route)
}


sealed class Route(val id: String){
    data object Ciudades: Route("ciudades")
    data class Clima(val lat: Double=0.0, val lon: Double=0.0): Route("clima")
}
