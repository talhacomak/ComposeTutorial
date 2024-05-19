package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

private val items = listOf("Hello", "World", "Compose")

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    ComposeTutorialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            EnhancedRecyclerView(items)
        }
    }
}


@Composable
fun ColorBox(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .background(color.value)
    )
}

val color = mutableStateOf(Color.Red)

@Composable
fun ColorBox2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .clickable {
                color.value = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            }
    )
}

@Composable
fun ShowSnackbar() {
    // val textFieldState = remember { mutableStateOf("") }
    var textFieldState by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(30.dp)
                .zIndex(2f)
        ) {
            TextField (value = textFieldState, onValueChange = {
                textFieldState = it
            }, singleLine = true, modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("hello $textFieldState",
                        duration = SnackbarDuration.Indefinite)
                }
            }) {
                Text("Show Snackbar")
            }
        }
    }
}

@Composable
fun EnhancedRecyclerView(items: List<String>) {
    LazyColumn {
        itemsIndexed(items) { _, item ->
            Text(text = item, modifier = Modifier
                .fillMaxWidth()
                .padding())
        }
    }
}

@Composable
fun EffectHandler() {

    var text by remember {
        mutableStateOf("")
    }


}
