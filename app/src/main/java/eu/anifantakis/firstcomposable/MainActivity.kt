package eu.anifantakis.firstcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.anifantakis.firstcomposable.ui.CounterViewModel
import eu.anifantakis.firstcomposable.ui.theme.FirstComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstComposableTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WrongCounterScreen()
                }
            }
        }
    }
}


@Composable
fun WrongCounterScreen() {
    // A plain Kotlin variable (not observable by Compose)
    var counter = 0

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // This Text will always display "0" because counter changes don't trigger recomposition.
        Text(text = "$counter", fontSize = 32.sp)
        Row {
            Button(onClick = { counter-- }) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { counter++ }) {
                Text(text = "+")
            }
        }
    }
}

@Composable
fun SimpleCounterScreen() {
    // Local state inside the composable
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count", fontSize = 32.sp)
        Row {
            Button(onClick = { count-- }) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { count++ }) {
                Text(text = "+")
            }
        }
    }
}

@Composable
fun HoistedCounterScreen() {
    // State variable that Compose observes
    var count by remember { mutableIntStateOf(0) }

    // Passing state and event handlers down to the stateless UI component
    CounterScreen(
        count = count,
        onIncrement = { count++ },
        onDecrement = { count-- }
    )
}

@Composable
fun CounterScreen(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count", fontSize = 32.sp)
        Row {
            Button(onClick = onDecrement) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onIncrement) {
                Text(text = "+")
            }
        }
    }
}

@Composable
fun CounterScreenWithViewModel(counterViewModel: CounterViewModel = viewModel()) {
    // Observing state from the ViewModel
    val count by counterViewModel.count

    // Reusing the same stateless UI component
    CounterScreen(
        count = count,
        onIncrement = { counterViewModel.increment() },
        onDecrement = { counterViewModel.decrement() }
    )
}