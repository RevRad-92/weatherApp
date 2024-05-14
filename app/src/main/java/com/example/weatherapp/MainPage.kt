package com.example.weatherapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun MainPage(){
    val viewModel: MainPageViewModel = viewModel()
    MainView(
        state = viewModel.uiState,
        onAction = {
            viewModel.ejecutarIntencion(it)
        }
    )
}

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    state: Estado,
    onAction: (Intencion)->Unit
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        when(state){
            is Estado.Error -> ErrorView(mensaje = state.mensaje)
            is Estado.Exitoso -> ClimaView(ciudad = state.ciudad, temperatura = state.temperatura, descripcion = state.descripcion  , st = state.st )
            Estado.Vacio -> EmptyView()
        }

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {onAction(Intencion.BorrarTodo)}) {
            Text(text = "Borrar todo")
        }

        Button(
            onClick = {onAction(Intencion.MostrarCABA)}
        ) {
            Text(text = "Mostrar CABA")
        }

        Button(
            onClick = {onAction(Intencion.MostrarCordoba)}
        ) {
            Text(text = "Mostrar Córdoba")
        }

        Button(
            onClick = {onAction(Intencion.MostrarError)}
        ) {
            Text(text = "Mostrar Error")
        }
    }

}

@Composable
fun ErrorView(mensaje: String){
    Text(text = mensaje)
}

@Composable
fun ClimaView(ciudad: String, temperatura: Int, descripcion: String, st: Int){
    Column {
        Text(text = ciudad, style = MaterialTheme.typography.titleMedium)
        Text(text = "${temperatura}°", style = MaterialTheme.typography.titleLarge)
        Text(text = descripcion, style = MaterialTheme.typography.bodyMedium)
        Text(text = "sensación térmica: ${st}°", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun EmptyView(){
    Text(text = "no hay nada para mostrar")
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    WeatherAppTheme {
        MainView(state = Estado.Vacio, onAction = {})
    }
}