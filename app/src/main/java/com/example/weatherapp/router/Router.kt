package com.example.weatherapp.router

interface Router{
    fun navegar(route: Route)
}


sealed class Route(val id: String){
    data object Ciudades: Route("ciudades")
    data class Clima(val lat: Float, val lon: Float, val nombre:String): Route("clima")
}
