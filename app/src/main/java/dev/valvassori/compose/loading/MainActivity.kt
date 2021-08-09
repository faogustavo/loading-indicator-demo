package dev.valvassori.compose.loading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.valvassori.compose.loading.indicators.BallPulseSyncIndicator
import dev.valvassori.compose.loading.indicators.BallPulseSyncIndicatorDelayAnimation
import dev.valvassori.compose.loading.indicators.BallScaleIndicator
import dev.valvassori.compose.loading.indicators.BallScaleIndicatorSideEffect
import dev.valvassori.compose.loading.indicators.InitialBallScaleIndicator
import dev.valvassori.compose.loading.indicators.TriangleSkewSpinIndicator
import dev.valvassori.compose.loading.ui.theme.LoadingIndicatorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadingIndicatorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = MaterialTheme.colors.background,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        BallScaleSamples()
                        BallPulseSamples()
                        TriangleSkewSample()
                    }
                }
            }
        }
    }
}

@Composable
fun BallScaleSamples() {
    Text(
        modifier = Modifier.padding(vertical = 6.dp),
        text = "BallScaleIndicator",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        NamedCompose(name = "None") {
            InitialBallScaleIndicator()
        }
        NamedCompose(name = "SideEffect") {
            BallScaleIndicatorSideEffect()
        }
        NamedCompose(name = "LaunchedEffect") {
            BallScaleIndicator()
        }
    }
}

@Composable
fun BallPulseSamples() {
    Text(
        modifier = Modifier.padding(vertical = 6.dp),
        text = "BallPulseSyncIndicator",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        NamedCompose(name = "Animation Delay") {
            BallPulseSyncIndicatorDelayAnimation()
        }
        NamedCompose(name = "Coroutines Delay") {
            BallPulseSyncIndicator()
        }
    }
}

@Composable
fun TriangleSkewSample() {
    Text(
        modifier = Modifier.padding(vertical = 6.dp),
        text = "TriangleSkewSpinIndicator",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )
    NamedCompose(name = "List items") {
        TriangleSkewSpinIndicator()
    }
}

@Composable
fun NamedCompose(
    name: String,
    Compose: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .border(1.dp, colorResource(id = R.color.black))
            .padding(4.dp)
            .width(92.dp)
            .defaultMinSize(minHeight = 64.dp),
    ) {
        Text(
            modifier = Modifier,
            text = name,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
        )
        Compose()
    }
}