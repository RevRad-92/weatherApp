package com.example.weatherapp.repository

import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.repository.models.Clima
import com.example.weatherapp.repository.models.ForecastDTO
import com.example.weatherapp.repository.models.ListForecast
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiRepository : Repository {

    private val apiKey = "0b278cab6a89a325866a0e96e605e13b"
//    private val urlGeo = "https://api.openweathermap.org/geo/1.0/direct"
//    private val urlData = "https://api.openweathermap.org/data/2.5/weather"

    private val cliente = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun searchCiudad(ciudad: String): List<Ciudad> {
        val response = cliente.get("https://api.openweathermap.org/geo/1.0/direct"){
            parameter("q",ciudad)
            parameter("limit",100)
            parameter("appid",apiKey)
        }
        if (response.status == HttpStatusCode.OK){
            val ciudades = response.body<List<Ciudad>>()
            return ciudades
        } else {
            throw Exception()
        }
    }

    override suspend fun getClima(lat: Float, lon: Float): Clima {
        val response = cliente.get("https://api.openweathermap.org/data/2.5/weather"){
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("units", "metric")
            parameter("appid", apiKey)
        }
        if (response.status == HttpStatusCode.OK){
            val clima = response.body<Clima>()
            return clima
        } else {
            throw Exception()
        }
    }

    override suspend fun getForecast(nombre: String): List<ListForecast> {
        val respuesta = cliente.get("https://api.openweathermap.org/data/2.5/forecast"){
            parameter("q",nombre)
            parameter("units","metric")
            parameter("appid",apiKey)
        }
        if (respuesta.status == HttpStatusCode.OK){
            val forecast = respuesta.body<ForecastDTO>()
            return forecast.list
        }else{
            throw Exception()
        }
    }
}