package com.example.weatherapp

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@SuppressLint("MissingPermission")
@Composable
fun WeatherScreen(city: String, viewModel: WeatherViewModel = viewModel()) {
    val weatherState by viewModel.weatherState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    //Gps:
    val context = LocalContext.current
    val fusedLoactionClient = LocationServices.getFusedLocationProviderClient(context)
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                fusedLoactionClient.getLastLocation().addOnSuccessListener { location ->
                    location?.let{
                        viewModel.fetchWeatherByLocation(
                            it.latitude,
                            it.longitude,
                            "4fdf306b50f58306ec6510dc5f5539ec"
                        )
                    }
                }
            }
        }
    LaunchedEffect(city) {
        viewModel.fetchWeather(city, "4fdf306b50f58306ec6510dc5f5539ec")
    }
    val backgroundBrush = Brush.verticalGradient(
        listOf(
            Color(0xFF4FACFE),
            Color(0xFF00F2FE)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(16.dp)
    ) {

        // 🔹 Top Row (GPS Button)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    when {
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED -> {

                            fusedLoactionClient.lastLocation
                                .addOnSuccessListener { location ->
                                    location?.let {
                                        viewModel.fetchWeatherByLocation(
                                            it.latitude,
                                            it.longitude,
                                            "4fdf306b50f58306ec6510dc5f5539ec"
                                        )
                                    }
                                }
                        }

                        else -> {
                            permissionLauncher.launch(
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "GPS",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 Weather Content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when {
                isLoading -> {
                    CircularProgressIndicator(color = Color.White)
                }

                errorMessage != null -> {
                    Text(
                        "Error: $errorMessage",
                        color = Color.White
                    )
                }

                weatherState != null -> {
                    val data = weatherState!!

                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Temperature: ${data.main.temp} °C",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White
                    )

                    Text(
                        text = "Condition: ${data.weather[0].description}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("MIN", color = Color.White.copy(alpha = 0.7f))
                            Text("${data.main.tempMin.toInt()}°", color = Color.White)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("MAX", color = Color.White.copy(alpha = 0.7f))
                            Text("${data.main.tempMax.toInt()}°", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}


