package com.example.weatherapp.repository

import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.repository.models.Clima
import com.example.weatherapp.repository.models.Clima2
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiRepository : Repository {

    private val ApiKey = "0b278cab6a89a325866a0e96e605e13b"
    private val urlGeo = "https://api.openweathermap.org/geo/1.0/direct"
    private val urlData = "https://api.openweathermap.org/data/2.5/weather"

    private val cliente = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun searchCiudad(ciudad: String): List<Ciudad> {
        val response = cliente.get("https://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit=100&appid=0b278cab6a89a325866a0e96e605e13b"){
//            parameter("q", ciudad)
//            parameter("limit", 5)
//            parameter("appid", ApiKey)
        }
        if (response.status == HttpStatusCode.OK){
            val ciudades = response.body<List<Ciudad>>()
            return ciudades
        } else {
            throw Exception()
        }
    }

    override suspend fun getClima(ciudad: Ciudad): Clima2 {
        val response = cliente.get(urlData){
            parameter("lat", ciudad.lat)
            parameter("lon", ciudad.lon)
            parameter("units", "metric")
            parameter("appid", ApiKey)
        }
        if (response.status == HttpStatusCode.OK){
            val clima = response.body<Clima2>()
            return clima
        } else {
            throw Exception()
        }
    }

    override suspend fun getForecast(ciudad: Ciudad): List<Clima2> {
        TODO("Not yet implemented")
    }
}