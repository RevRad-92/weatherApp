package com.example.weatherapp.router

import androidx.navigation.NavHostController


class Enrutador(
    val navHostController: NavHostController
): Router {
    override fun navegar(route: Route) {
        navHostController.navigate(route.id)
    }

}