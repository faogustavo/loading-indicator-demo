package dev.valvassori.compose.loading.indicators

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.valvassori.compose.loading.shapes.Ball
import kotlinx.coroutines.delay

@Composable
fun BallPulseSyncIndicatorDelayAnimation() {
    val animationValues = (1..3).map { index ->
        var animatedValue by remember { mutableStateOf(0f) }

        LaunchedEffect(key1 = Unit) {
            animate(
                initialValue = 0f,
                targetValue = 12f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 300,
                        delayMillis = 70 * index,
                    ),
                    repeatMode = RepeatMode.Reverse,
                )
            ) { value, _ -> animatedValue = value }
        }

        animatedValue
    }

    Row(
        modifier = Modifier.height(24.dp),
    ) {
        animationValues.forEach { animatedValue ->
            Ball(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .offset(y = animatedValue.dp),
            )
        }
    }
}

@Composable
fun BallPulseSyncIndicator() {
    val animationValues = (1..3).map { index ->
        var animatedValue by remember { mutableStateOf(0f) }

        LaunchedEffect(key1 = Unit) {
            // Delaying using Coroutines
            delay(70L * index)

            animate(
                initialValue = 0f,
                targetValue = 12f,
                animationSpec = infiniteRepeatable(
                    // Remove delay property
                    animation = tween(durationMillis = 300),
                    repeatMode = RepeatMode.Reverse,
                )
            ) { value, _ -> animatedValue = value }
        }

        animatedValue
    }

    Row(
        modifier = Modifier.height(24.dp),
    ) {
        animationValues.forEach { animatedValue ->
            Ball(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .offset(y = animatedValue.dp),
            )
        }
    }
}