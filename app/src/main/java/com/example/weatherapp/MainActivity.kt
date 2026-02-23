package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.navigation.WeatherNavigation
import com.example.weatherapp.ui.theme.WhetherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           WeatherNavigation()
        }
    }
}

//Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ){
//        OutlinedTextField(
//            value = city,
//            onValueChange = { city = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = { Text("Enter city name") }
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Button(
//            onClick = { viewModel.fetchWeather(city, "4fdf306b50f58306ec6510dc5f5539ec") },
//            modifier = Modifier.fillMaxWidth(),
//        ){
//            Text("Get Weather")
//        }
//        when {
//            isLoading -> {
//                CircularProgressIndicator()
//            }
//            errorMessage != null -> {
//                Text("Error: $errorMessage")
//            }
//            weatherState != null -> {
//                val data = weatherState!!
//                Text("City: ${data.name}")
//                Text("Temperature: ${data.main.temp} °C")
//                Text("Min: ${data.main.tempMin} °C")
//                Text("Max: ${data.main.tempMax} °C")
//                Text("Condition: ${data.weather[0].description}")
//            }
//        }
//    }


