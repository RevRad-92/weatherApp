package com.example.weatherapp.presentacion.clima

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.router.Router
import kotlinx.coroutines.launch

class ClimaViewModel(
    val repository: Repository,
    val router: Router
) : ViewModel() {



    var uiState by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)



    fun ejecutar(intencion: ClimaIntencion){
        when(intencion){
            ClimaIntencion.BorrarTodo-> borrarTodo()
            ClimaIntencion.MostrarCordoba -> mostrarCordoba()
            ClimaIntencion.MostrarError -> mostrarError()
            ClimaIntencion.MostrarCABA -> mostrarCaba()
        }
    }

    private fun mostrarError(){
        uiState = ClimaEstado.Error("Este es un error de mentira")
    }
    private fun  borrarTodo(){
        uiState = ClimaEstado.Vacio
    }

    private fun mostrarCaba(){

    }

    private fun mostrarCordoba(){

        ClimaEstado.Cargando
        viewModelScope.launch {
            val cordoba = Ciudad(name = "Cordoba", lat = -31.4135, lon = -64.18105, state = "Ar")
            try{
                val clima = repository.getClima(cordoba)
                ClimaEstado.Exitoso(
                    ciudad = clima.name ,
                    temperatura = 10.0,//clima.main.temp,
                    descripcion = "asd",//clima.weather.first().description,
                    st = 10.2//clima.main.feelsLike,
                )
            } catch (exception: Exception){
                ClimaEstado.Error("Error")
            }


        }

    }


}

class ClimaViewModelFactory(
    private val repository: Repository,
    private val router: Router
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModelFactory::class.java)) {
            return ClimaViewModelFactory(repository, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}