package com.addas.algovisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.ViewModel
import com.addas.algovisualizer.ui.theme.AlgoVisualizerTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgoVisualizerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BarGraph(viewModel)
                }
            }
        }
    }
}

@Composable
fun BarGraph(viewModel: MyViewModel) {
    val data = viewModel.data
    var animatingData by remember { mutableStateOf(data) }

    LaunchedEffect(data) {
        animatingData = data
    }

    val barWidth = 40.dp
    val barSpacing = 10.dp
    val barHeightMultiplier = 20.dp

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    barHeightMultiplier * data
                        .maxOrNull()!!
                        .toFloat() + barSpacing * 2
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            animatingData.forEachIndexed { index, item ->
                val targetHeight = item * barHeightMultiplier
                val transition = updateTransition(targetHeight, label = "barHeight$index")
                val barHeight by transition.animateDp(
                    transitionSpec = {
                        tween(
                            durationMillis = 500,
                            easing = LinearEasing
                        )
                    },    //Lower duration value would be idle for this
                    label = ""
                ) { targetHeight }

                Box(
                    modifier = Modifier
                        .width(barWidth)
                        .height(barHeight)
                        .background(Color.Blue)
                        .padding(horizontal = barSpacing / 2, vertical = barSpacing),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item.toString(), color = Color.White)
                }
            }
        }

        Button(onClick = { viewModel.shuffleData() }) {
            Text("Shuffle")
        }
    }
}

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {
    var data by mutableStateOf(listOf(8, 4, 2, 9, 1, 5, 7, 3, 6))

    fun shuffleData() {
        data = data.shuffled()
    }

    fun sortArr(arr: MutableList<Int>) {
        // Bubble Sort
        CoroutineScope(Dispatchers.IO).launch {
            for (i in arr.indices) {
                for (j in 0 until arr.size - i - 1) {
                    // Highlight the two bars being compared
                    arr[j] = arr[j] * -1
                    arr[j + 1] = arr[j + 1] * -1

                    // Delay for visualization purposes
                    delay(1000)

                    if (arr[j] > arr[j + 1]) {
                        // Swap the two bars
                        val temp = arr[j]
                        arr[j] = arr[j + 1]
                        arr[j + 1] = temp

                        // Delay for visualization purposes
                        delay(1000)
                    }

                    // Un-highlight the two bars
                    arr[j] = arr[j] * -1
                    arr[j + 1] = arr[j + 1] * -1
                }
            }
        }
    }

}