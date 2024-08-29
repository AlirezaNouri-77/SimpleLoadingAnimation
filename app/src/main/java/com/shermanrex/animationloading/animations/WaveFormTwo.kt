package com.shermanrex.animationloading.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.shermanrex.animationloading.ui.theme.AnimationLoaingTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun WaveFormTwo(
  modifier: Modifier = Modifier,
  enable: Boolean = true,
  size: DpSize = DpSize(60.dp, 60.dp),
  waveColor: Color = Color.Black,
  density: Density = LocalDensity.current,
) {

  val lineSpace = 10f
  val lineWidth = 7f
  val width = with(density) {
    size.width.toPx()
  }
  val height = with(density) {
    size.height.toPx()
  }

  val lineCount = ((width - (lineSpace)) / (lineSpace + lineWidth)).roundToInt()
  val halfHeight = height / 2

  val animatable = remember {
    Array(lineCount) { Animatable(initialValue = 0f) }
  }

  if (enable) {
    LaunchedEffect(Unit) {
      (0 until lineCount).forEachIndexed { index, _ ->
        val targetValueStartAndEnd = halfHeight / 1.5f
        launch(Dispatchers.Main.immediate) {
          delay(45L * (5..10).random())
          animatable[index].animateTo(
            targetValue = targetValueStartAndEnd,
            infiniteRepeatable(
              animation = tween(
                durationMillis = (400..600).random(),
                easing = LinearEasing,
              ),
              repeatMode = RepeatMode.Reverse,
            ),
          )
        }
      }
    }
  } else {
    LaunchedEffect(Unit) {
      (0 until lineCount).forEachIndexed { index, _ ->
        animatable[index].stop()
        launch(Dispatchers.Main.immediate) {
          animatable[index].animateTo(
            targetValue = 0f,
          )
        }
      }
    }
  }


  Box(
    modifier = modifier
      .size(size),
  ) {

    Canvas(
      modifier = Modifier
        .size(size)
    ) {

      for (index in 0 until lineCount) {
        if (index == lineCount - 1 || index == 0) continue
        drawLine(
          color = waveColor.copy(alpha = 0.7f),
          start = Offset(x = (index * (lineWidth + lineSpace)) + lineSpace, y = halfHeight + animatable[index].value),
          end = Offset(x = (index * (lineWidth + lineSpace)) + lineSpace, y = halfHeight - animatable[index].value),
          strokeWidth = lineWidth,
          cap = StrokeCap.Round,
        )
      }
    }

  }

}

@Preview(showBackground = true)
@Composable
private fun WavePreview() {
  AnimationLoaingTheme {
    WaveFormTwo()
  }
}