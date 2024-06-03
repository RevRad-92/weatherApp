package com.example.weatherapp.repository

import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.repository.models.Clima
import com.example.weatherapp.repository.models.Clima2

interface Repository {
    suspend fun searchCiudad(ciudad: String): List<Ciudad>
    suspend fun getClima(ciudad: Ciudad): Clima2
    suspend fun getForecast(ciudad: Ciudad):List<Clima2>
}