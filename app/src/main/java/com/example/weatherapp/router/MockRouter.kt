package com.example.weatherapp.router

class MockRouter : Router {
    override fun navegar(route: Route) {
        println("navegar a: ${route.id}")
    }
}