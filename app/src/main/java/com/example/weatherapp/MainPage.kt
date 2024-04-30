package com.example.weatherapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    MainView( viewModel = viewModel())
}

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    viewModel: MainPageViewModel
) {

    val ViewModel : MainPageViewModel = viewModel();

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (viewModel.noHayDatos.value){
            Text(text = "no hay nada para mostrar")
        } else {
            Text(text = ViewModel.ciudad.value, style = MaterialTheme.typography.titleMedium)

            Text(text = "${ViewModel.temperatura.value}°", style = MaterialTheme.typography.titleLarge)
            Text(text = ViewModel.descripcion.value, style = MaterialTheme.typography.bodyMedium)
            Text(text = "sensación térmica: ${ViewModel.st.value}°", style = MaterialTheme.typography.bodyMedium)
        }


        Button(
            onClick = {viewModel.borrarTodo()}) {
            Text(text = "Borrar todo")
        }

        Button(
            onClick = {viewModel.mostrarCABA()}
        ) {
            Text(text = "Mostrar CABA")
        }

        Button(
            onClick = {viewModel.mostrarCordoba()}
        ) {
            Text(text = "Mostar Córdoba")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    WeatherAppTheme {
        MainView(viewModel = viewModel())
    }
}