package com.jazminbustillos.starwarsfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jazminbustillos.starwarsfinder.model.Personaje
import com.jazminbustillos.starwarsfinder.repository.StarWarsRepository
import com.jazminbustillos.starwarsfinder.ui.theme.StarWarsFinderTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsFinderTheme {
                StarWarsApp()
            }
        }
    }

}

@Composable
fun StarWarsApp() {

    val repository = remember { StarWarsRepository() }

    var personajes by remember {
        mutableStateOf<List<Personaje>>(emptyList())
    }

    var busqueda by remember {
        mutableStateOf("")
    }

    var cargando by remember {
        mutableStateOf(true)
    }

    var exitoApi by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {

            val resultado = repository.obtenerPersonajes()

            personajes = resultado.personajes
            exitoApi = resultado.exito
            cargando = false
        }
    }

    val filtrados = personajes.filter {
        it.nombre.contains(busqueda, ignoreCase = true)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.space),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Buscador de Personajes\nde Star Wars",
                color = Color.Yellow,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            when {
                cargando -> {
                    CircularProgressIndicator(
                        color = Color.Yellow
                    )
                }

                exitoApi -> {
                    Text(
                        text = "✅ Conexión exitosa con SWAPI",
                        color = Color.Green
                    )
                }

                else -> {
                    Text(
                        text = "❌ Error al conectar con SWAPI",
                        color = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                label = {
                    Text("Buscar personaje")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Yellow,
                    focusedLabelColor = Color.Yellow,
                    unfocusedLabelColor = Color.Yellow,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.Yellow
                ),
                shape = RoundedCornerShape(18.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                items(filtrados) { personaje ->
                    TarjetaPersonaje(personaje)
                }
            }
        }
    }
}

@Composable
fun TarjetaPersonaje(personaje: Personaje) {

    val colorBorde =
        if (personaje.esJedi) Color.Cyan else Color.Red

    val colorTitulo =
        if (personaje.esJedi) Color.Cyan else Color.Red

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 3.dp,
                color = colorBorde,
                shape = RoundedCornerShape(24.dp)
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC111111)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = personaje.imagen),
                    contentDescription = personaje.nombre,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(18.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = personaje.nombre,
                color = Color.Yellow,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = personaje.subtitulo,
                color = colorTitulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Edad: ${personaje.edad}",
                color = Color.White,
                fontSize = 15.sp
            )

            Text(
                text = "Altura: ${personaje.altura}",
                color = Color.White,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = personaje.descripcion,
                color = Color.LightGray,
                fontSize = 15.sp
            )
        }
    }

}
