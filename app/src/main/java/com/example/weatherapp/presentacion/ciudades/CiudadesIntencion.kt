package com.example.weatherapp.presentacion.ciudades

import com.example.weatherapp.repository.models.Ciudad

sealed class CiudadesIntencion {
    data class Buscar(val nombre:String): CiudadesIntencion()
    data class Seleccionar(val ciudad: Ciudad): CiudadesIntencion()
    //data object UsarGeo: CiudadesIntencion()
}