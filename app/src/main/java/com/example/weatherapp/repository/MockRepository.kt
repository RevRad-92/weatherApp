package com.example.weatherapp.repository

import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.repository.models.Clima
import com.example.weatherapp.repository.models.ListForecast


class MockRepository  : Repository {
    override suspend fun searchCiudad(ciudad: String): List<Ciudad> {
        val ciudad1 = Ciudad(
            name = "King's Landing",
            lat = 23.0f,
            lon = 24.3f,
            country = "Argentina"
        )
        val ciudad2 = Ciudad(
            name = "Winterfell",
            lat = 23.0f,
            lon = 24.3f,
            country = "Argentina"
        )
        val ciudad3 = Ciudad(
            name = "Harrental",
            lat = 23.0f,
            lon = 24.3f,
            country = "Argentina"
        )
        return listOf(ciudad1, ciudad2, ciudad3)
    }

    override suspend fun getClima(lat: Float, lon: Float): Clima {
        TODO("Not yet implemented")
    }

    override suspend fun getForecast(nombre: String): List<ListForecast> {
        TODO("Not yet implemented")
    }
}