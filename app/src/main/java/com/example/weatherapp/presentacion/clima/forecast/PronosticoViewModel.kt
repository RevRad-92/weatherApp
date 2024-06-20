package com.example.weatherapp.presentacion.clima.forecast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.router.Router
import kotlinx.coroutines.launch

class PronosticoViewModel(
    val repository: Repository,
    val router: Router,
    val nombre: String
) : ViewModel() {

    var uiState by mutableStateOf<PronosticoEstado>(PronosticoEstado.Vacio)

    fun ejecutar(intencion: PronosticoIntencion){
        when(intencion){
            PronosticoIntencion.actualizarClima -> getForecast()
        }
    }

    fun getForecast() {
        uiState = PronosticoEstado.Cargando
        viewModelScope.launch {
            try{
                val clima = repository.getForecast(nombre).filter{
                    //TODO
                    true
                }
                uiState = PronosticoEstado.Exitoso(                    clima)
            } catch (exception: Exception) {
                uiState = PronosticoEstado.Error(exception.localizedMessage ?: "Error desconocido")
            }
        }

    }    }

class PronosticoViewModelFactory(
    private val repository: Repository,
    private val router: Router,
    private val nombre: String,
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PronosticoViewModelFactory::class.java)) {
            return PronosticoViewModelFactory(repository, router, nombre) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}