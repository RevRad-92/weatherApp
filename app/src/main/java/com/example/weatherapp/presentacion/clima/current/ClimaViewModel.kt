package com.example.weatherapp.presentacion.clima.current

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.router.Router
import kotlinx.coroutines.launch

class ClimaViewModel(
    val repository: Repository,
    val router: Router,
    val lat : Float,
    val lon : Float,
    val nombre: String
) : ViewModel() {

    var uiState by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    fun ejecutar(intencion: ClimaIntencion){
        when(intencion){
            ClimaIntencion.actualizarClima -> getClima()
        }
    }

    fun getClima() {
        uiState = ClimaEstado.Cargando
        viewModelScope.launch {
            try{
                val clima = repository.getClima(lat = lat, lon = lon)
                uiState = ClimaEstado.Exitoso(
                    ciudad = clima.name,
                    temperatura = clima.main.temp,
                    descripcion = clima.weather.first().description,
                    st = clima.main.feelsLike
                )
            } catch (exception: Exception) {
                uiState = ClimaEstado.Error(exception.localizedMessage ?: "Error desconocido")
            }
        }

    }    }


//    private fun mostrarError(){
//        uiState = ClimaEstado.Error("Este es un error de mentira")
//    }
//    private fun  borrarTodo(){
//        uiState = ClimaEstado.Vacio
//    }
//
//}

class ClimaViewModelFactory(
    private val repository: Repository,
    private val router: Router,
    private val lat: Float,
    private val lon: Float,
    private val nombre: String,
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModelFactory::class.java)) {
            return ClimaViewModelFactory(repository, router, lat, lon, nombre) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}