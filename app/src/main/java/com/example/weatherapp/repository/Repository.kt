package com.example.weatherapp.repository

import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.repository.models.Clima
import com.example.weatherapp.repository.models.ListForecast

interface Repository {
    suspend fun searchCiudad(ciudad: String): List<Ciudad>
    suspend fun getClima(lat: Float, lon: Float): Clima
    suspend fun getForecast(nombre: String):List<ListForecast>
}