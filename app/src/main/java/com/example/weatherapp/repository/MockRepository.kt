package com.example.weatherapp.repository

import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.repository.models.Clima
import com.example.weatherapp.repository.models.Clima2

class MockRepository  : Repository {
    override suspend fun searchCiudad(ciudad: String): List<Ciudad> {
        val ciudad1 = Ciudad(
            name = "jojo",
            lat = 23.0,
            lon = 24.3,
            state = "Argentina"
        )
        val ciudad2 = Ciudad(
            name = "jojo",
            lat = 23.0,
            lon = 24.3,
            state = "Argentina"
        )
        val ciudad3 = Ciudad(
            name = "jojo",
            lat = 23.0,
            lon = 24.3,
            state = "Argentina"
        )
        return listOf(ciudad1, ciudad2, ciudad3)
    }

    override suspend fun getClima(ciudad: Ciudad): Clima2 {
        TODO("Not yet implemented")
    }

    override suspend fun getForecast(ciudad: Ciudad): List<Clima2> {
        TODO("Not yet implemented")
    }
}