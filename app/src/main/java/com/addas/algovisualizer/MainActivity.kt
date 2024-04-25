package com.addas.algovisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.addas.algovisualizer.ui.selection_sort.SelectionSortViewModel
import com.addas.algovisualizer.ui.theme.AlgoVisualizerTheme
import com.addas.algovisualizer.util.Navigation
import com.addas.algovisualizer.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgoVisualizerTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(modifier = Modifier.padding(10.dp), onClick = {
            navController.navigate(Screen.SelectionSortScreen.route)
        }) {
            Text(text = "Selection Sort")
        }
    }
}

@Composable
fun BarGraph(array: List<Int>, currentElement: Int) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
        val barWidth = size.width / array.size

        array.forEachIndexed { index, value ->
            val barHeight = value.toFloat() / array.maxOrNull()!! * size.height
            val barColor = if (index == currentElement) Color.Red else Color.Blue

            drawRect(
                color = barColor,
                topLeft = Offset(index * barWidth, size.height - barHeight),
                size = Size(barWidth, barHeight)
            )
        }
    }
}