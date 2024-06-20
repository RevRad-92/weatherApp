package com.example.weatherapp.presentacion.clima

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.presentacion.clima.current.ClimaView
import com.example.weatherapp.presentacion.clima.current.ClimaViewModel
import com.example.weatherapp.presentacion.clima.current.ClimaViewModelFactory
import com.example.weatherapp.presentacion.clima.forecast.PronosticoView
import com.example.weatherapp.presentacion.clima.forecast.PronosticoViewModel
import com.example.weatherapp.presentacion.clima.forecast.PronosticoViewModelFactory
import com.example.weatherapp.repository.ApiRepository
import com.example.weatherapp.router.Enrutador

@Composable
fun ClimaPage(
    navHostController: NavHostController,
    lat : Float,
    lon : Float,
    nombre: String
){
    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            repository = ApiRepository(),
            router = Enrutador(navHostController),
            lat = lat,
            lon = lon,
            nombre = nombre
        )
    )
    val pronosticoViewModel : PronosticoViewModel = viewModel(
        factory = PronosticoViewModelFactory(
            repository = ApiRepository(),
            router = Enrutador(navHostController),
            nombre = nombre
        )
    )
    Column {
        ClimaView(
            state = viewModel.uiState,
            onAction = {
                viewModel.ejecutar(it)
            }
        )
        PronosticoView(
            state = pronosticoViewModel.uiState,
            onAction = {
                pronosticoViewModel.ejecutar(it)
            }
        )

    }

}

