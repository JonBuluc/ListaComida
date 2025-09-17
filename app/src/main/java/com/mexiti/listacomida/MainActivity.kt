package com.mexiti.listacomida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.listacomida.data.DataSource
import com.mexiti.listacomida.model.Platillo
import com.mexiti.listacomida.ui.theme.ListaComidaTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaComidaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MenuApp()

                }
            }
        }
    }
}
//Se agrego con el Scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuApp(){
    MenuCardList(
        platilloList = DataSource().LoadPlatillos(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCardList( platilloList:List<Platillo>, modifier: Modifier = Modifier ){
    Scaffold (
        topBar = {
            MenuTopAppBar()
        }
    ){it ->
        LazyColumn( contentPadding = it ){
            items(platilloList){
                platillo -> MenuCard(
                platillo = platillo,
                    modifier= Modifier.padding(10.dp)
            )
            }

        }
    }
}



@Composable
fun MenuCard(platillo: Platillo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = platillo.drawableResourceId),
                contentDescription = stringResource(id = platillo.stringResourceId),
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {

                Text(
                    text = "Pozole",
                    //Use unas letras mas pequeñas pero igual cambie
                    //el tamaño con el dp
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )

                Text(
                    text = "MX $100",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                Text(
                    text = "Ahorra hasta el 30%",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
        }
    }
}

//Esta parte la agregue con ayuda de gemini
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF82B1FF))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_commudel),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Commudel",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MenuPlatilloPreview() {
    ListaComidaTheme(darkTheme = false) {
        MenuCard(platillo = Platillo(R.string.pozole,R.drawable.pozole) )
    }
}
@Preview
@Composable
fun MenuTopAppBarPreview(){
    ListaComidaTheme {
        MenuTopAppBar()
    }
}