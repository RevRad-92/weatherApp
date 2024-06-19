package com.example.weatherapp.presentacion.clima

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.repository.ApiRepository
import com.example.weatherapp.router.Enrutador

@Composable
fun ClimaPage(
    navHostController: NavHostController
){
    val viewModel: ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            repository = ApiRepository(),
            router = Enrutador(navHostController)
        )
    )
    ClimaView(
        state = viewModel.uiState,
        onAction = {
            viewModel.ejecutar(it)
        }
    )
}

