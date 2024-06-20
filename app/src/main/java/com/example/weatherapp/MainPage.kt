package com.example.weatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            route = "clima?lat={lat}&lon={lon}&nombre={nombre}",
            arguments =  listOf(
                navArgument("lat") { type= NavType.FloatType },
                navArgument("lon") { type= NavType.FloatType },
                navArgument("nombre") { type= NavType.StringType }
            )
        ) {
            val lat = it.arguments?.getFloat("lat") ?: 0.0f
            val lon = it.arguments?.getFloat("lon") ?: 0.0f
            val nombre = it.arguments?.getString("nombre") ?: ""
            ClimaPage(navHostController, lat = lat, lon = lon, nombre = nombre)
        }
    }
}