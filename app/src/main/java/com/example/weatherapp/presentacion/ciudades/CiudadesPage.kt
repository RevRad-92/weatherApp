package com.example.weatherapp.presentacion.ciudades

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.repository.ApiRepository
import com.example.weatherapp.router.Enrutador


@Composable
fun CiudadesPage(
    navHostController: NavHostController
){
    val viewModel: CiudadesViewModel = viewModel(
        factory = CiudadesViewModelFactory(
            repository = ApiRepository(),
            router = Enrutador(navHostController)
//            repository = MockRepository(),
//            router = MockRouter()
        )
    )

    CiudadesView(
        state = viewModel.uiState,
        onAction = {
            viewModel.ejecutar(it)
        }
    )
}

