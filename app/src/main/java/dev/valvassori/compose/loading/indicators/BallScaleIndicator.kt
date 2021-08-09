package dev.valvassori.compose.loading.indicators

import android.util.Log
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import dev.valvassori.compose.loading.shapes.Ball

private const val TAG = "BallScaleIndicator"

@Composable
fun InitialBallScaleIndicator() {
    val animationProgress by animateFloatAsState(
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800)
        )
    )

    Ball(
        modifier = Modifier
            .scale(animationProgress)
            .alpha(1 - animationProgress),
        size = 48.dp,
    )
}

@Composable
fun BallScaleIndicatorSideEffect() {
    // Create one target value state that will
    // change to start the animation
    var targetValue by remember { mutableStateOf(0f) }

    // Update the attribute on the animation
    val animationProgress by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800)
        )
    )

    // Use the SideEffect helper to run something
    // when this block runs
    SideEffect {
        Log.d(TAG, "SideEffect() called")
        targetValue = 1f
    }

    Ball(
        modifier = Modifier
            .scale(animationProgress)
            .alpha(1 - animationProgress),
        size = 48.dp,
    )
}

@Composable
fun BallScaleIndicator() {
    var animationProgress by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit) {
        Log.d(TAG, "LaunchedEffect() called")
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800)
            )
        ) { value, _ -> animationProgress = value }
    }

    Ball(
        modifier = Modifier
            .scale(animationProgress)
            .alpha(1 - animationProgress),
        size = 48.dp,
    )
}