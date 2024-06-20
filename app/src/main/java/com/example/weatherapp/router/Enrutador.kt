package com.example.weatherapp.router

import android.annotation.SuppressLint
import androidx.navigation.NavHostController


class Enrutador(
    val navHostController: NavHostController
): Router {
    @SuppressLint("DefaultLocale")
    override fun navegar(route: Route) {
        when(route){
            Route.Ciudades -> navHostController.navigate(route.id)

            is Route.Clima -> {
                val route = String.format(format="%s?lat=%f&lon=%f&nombre=%s",route.id,route.lat,route.lon,route.nombre)
                navHostController.navigate(route)
            }
        }

    }

}