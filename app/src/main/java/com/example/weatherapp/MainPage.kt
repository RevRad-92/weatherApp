package com.example.weatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentacion.ciudades.CiudadesPage
import com.example.weatherapp.presentacion.clima.ClimaPage
import com.example.weatherapp.router.Enrutador
import com.example.weatherapp.router.Route

@Composable
fun MainPage(){
    val navHostController = rememberNavController()
    val router = Enrutador(navHostController)
    NavHost(
        navController = navHostController,
        startDestination = Route.Ciudades.id
    ){
        composable(
            route = Route.Ciudades.id
        ){
            CiudadesPage(navHostController)
        }
        composable(
            route = Route.Clima().id
        ){
            ClimaPage(navHostController)
        }
    }
}