package dev.valvassori.compose.loading.indicators

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import dev.valvassori.compose.loading.shapes.Triangle

@Composable
fun TriangleSkewSpinIndicator() {
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(
            durationMillis = 2500,
            easing = LinearEasing,
        )
    )
    val xRotation by animateValues(
        values = listOf(0f, 180f, 180f, 0f, 0f),
        animationSpec = animationSpec
    )
    val yRotation by animateValues(
        values = listOf(0f, 0f, 180f, 180f, 0f),
        animationSpec = animationSpec
    )

    Triangle(
        modifier = Modifier.graphicsLayer(
            rotationX = xRotation,
            rotationY = yRotation,
        )
    )
}

@Composable
fun animateValues(
    values: List<Float>,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> {
    // 1. Create the groups zipping with next entry
    val groups by rememberUpdatedState(newValue = values.zipWithNext())

    // 2. Start the state with the first value
    val state = remember { mutableStateOf(values.first()) }

    LaunchedEffect(key1 = groups) {
        val (_, setValue) = state

        // Start the animation from 0 to groups quantity
        animate(
            initialValue = 0f,
            targetValue = groups.size.toFloat(),
            animationSpec = animationSpec,
        ) { frame, _ ->
            // Get which group is being evaluated
            val integerPart = frame.toInt()
            val (initialValue, finalValue) = groups[frame.toInt()]

            // Get the current "position" from the group animation
            val decimalPart = frame - integerPart

            // Calculate the progress between the initial and final value
            setValue(
                initialValue + (finalValue - initialValue) * decimalPart
            )
        }
    }

    return state
}