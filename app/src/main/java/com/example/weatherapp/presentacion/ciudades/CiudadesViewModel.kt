package com.example.weatherapp.presentacion.ciudades

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.router.Route
import com.example.weatherapp.router.Router
import kotlinx.coroutines.launch

class CiudadesViewModel (
    val repository: Repository,
    val router: Router
) : ViewModel(){

    var uiState by mutableStateOf<CiudadesEstado>(CiudadesEstado.Vacio)
    fun ejecutar(intencion: CiudadesIntencion){
        when(intencion){
            is CiudadesIntencion.Buscar -> buscar(nombre = intencion.nombre)
            is CiudadesIntencion.Seleccionar -> seleccionar(ciudad = intencion.ciudad)
            //CiudadesIntencion.UsarGeo -> usarGeo()
        }
    }

    private fun buscar(nombre:String){

        if (nombre.count() < 3) {
            return
        }
        uiState = CiudadesEstado.Cargando
        viewModelScope.launch{
            try{
                val ciudades = repository.searchCiudad(nombre)
                uiState = CiudadesEstado.Resultado(ciudades)
            } catch (exception: Exception){
                uiState = CiudadesEstado.Error(exception.message?:"Error: no se puede buscar ciudad")
            }

        }
    }
    private fun seleccionar(ciudad: Ciudad){
        val ruta = Route.Clima(
            lat = ciudad.lat,
            lon = ciudad.lon,
            nombre = ciudad.name
        )
        router.navegar(ruta)
    }

    private fun usarGeo(){
        // TODO acceder a la geo del dispositivo
    }
}

class CiudadesViewModelFactory(
    private val repository: Repository,
    private val router: Router
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadesViewModelFactory::class.java)) {
            return CiudadesViewModelFactory(repository, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

