package com.example.weatherapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun MainPage(modifier: Modifier = Modifier) {

    // variable observada (remember para q no se pierda al recomponerse)
    val ciudad = remember {mutableStateOf<String>("Caba")}
    val temperatura = remember {mutableStateOf<Int>(14)}
    val descripcion = remember {mutableStateOf<String>("Nublado")}
    val st = remember {mutableStateOf<Int>(13)}

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = ciudad.value, style = MaterialTheme.typography.titleMedium)

        Text(text = "${temperatura.value}°", style = MaterialTheme.typography.titleLarge)
        Text(text = descripcion.value, style = MaterialTheme.typography.bodyMedium)
        Text(text = "sensación térmica: ${st.value}°", style = MaterialTheme.typography.bodyMedium)
    }
    
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    WeatherAppTheme {
        MainPage()
    }
}