package com.addas.algovisualizer.ui.selection_sort

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.addas.algovisualizer.BarGraph

@Composable
fun SelectionSortScreen(viewModel: SelectionSortViewModel) {

    var speedSliderPosition by remember {
        mutableFloatStateOf(1f)
    }
    var arraySizeSliderPosition by remember {
        mutableFloatStateOf(0.1f)
    }


    Column {
        BarGraph(
            array = viewModel.array,
            currentElement = viewModel.currentIndex.value
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (viewModel.runningStatus.value)
                    viewModel.activateKillSwitch()
                else
                    viewModel.selectionSort()
            }) {
            Text(text = if (viewModel.runningStatus.value) "Stop" else "Start")
        }

        //Speed Slider
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Speed", Modifier.padding(5.dp))
            Slider(
                value = speedSliderPosition,
                onValueChange = { floatValue ->
                    speedSliderPosition = floatValue
                    //Calculating delay based on float value and arraySize
                    viewModel.speed.value =
                        if (floatValue == 1f)
                            0.001f * viewModel.arraySize.value / 100
                        else
                            (1 - floatValue) * viewModel.arraySize.value / 100
                }
            )
        }

        AnimatedVisibility(visible = !viewModel.runningStatus.value) {
            Column {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.resetArray()
                    }) {
                    Text(text = "Regenerate Array")
                }

                //Array Size Slider
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Array Size", Modifier.padding(5.dp))
                    Slider(
                        value = arraySizeSliderPosition,
                        onValueChange = { floatValue ->
                            arraySizeSliderPosition = floatValue
                            if (floatValue >= 0.01f)
                                viewModel.arraySize.value =
                                    (floatValue * 100).toInt()
                            else
                                viewModel.arraySize.value = 2

                            viewModel.speed.value =
                                if (speedSliderPosition == 1f)
                                    0.001f * viewModel.arraySize.value / 100
                                else
                                    (1 - speedSliderPosition) * viewModel.arraySize.value / 100
                            viewModel.resetArray()
                        })
                }

            }
        }

        Text(text = "Current Index : ${viewModel.currentIndex.value}")
        Text(text = "Current Sub Index : ${viewModel.currentSubIndex.value}")
        Text(text = "Current Minimum Index : ${viewModel.minimumIndex.value}")

    }

}