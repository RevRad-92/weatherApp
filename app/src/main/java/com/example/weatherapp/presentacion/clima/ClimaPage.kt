package com.example.weatherapp.presentacion.clima

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainPage(){
    val viewModel: ClimaViewModel = viewModel()
    ClimaView(
        state = viewModel.uiState,
        onAction = {
            viewModel.ejcutar(it)
        }
    )
}
