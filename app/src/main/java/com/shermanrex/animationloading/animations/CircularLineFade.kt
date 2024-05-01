package com.shermanrex.animationloading.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularLineFade(
  modifier: Modifier = Modifier,
  lineCount: Int = 10,
  lineColor: Color = Color.Black,
  size: Dp = 18.dp,
) {

  val animate = remember {
    Array(lineCount) {
      Animatable(0.3f)
    }
  }

  (1..lineCount).forEachIndexed { index, _ ->
    ApplyAnimation(animatable = animate[index], index = index, lineCount = lineCount)
  }

  Box(
    modifier = modifier.size(size),
    contentAlignment = Alignment.Center,
  ) {
    Canvas(
      modifier = Modifier
        .matchParentSize(),
    ) {
      (1..lineCount).forEachIndexed { index, i ->

        val angel = Math.PI / (lineCount / 2) * i - 3.5
        val startX = this.size.width / 2 + cos(angel) * this.size.width / 2
        val endX = this.size.width / 2 + cos(angel) * this.size.width / 3.5
        val startY = this.size.width / 2 + sin(angel) * this.size.width / 2
        val endY = this.size.width / 2 + sin(angel) * this.size.width / 3.5

        drawLine(
          color = lineColor,
          start = Offset(x = startX.toFloat(), y = startY.toFloat()),
          end = Offset(x = endX.toFloat(), y = endY.toFloat()),
          cap = StrokeCap.Round,
          alpha = animate[index].value,
          strokeWidth = this.size.width / lineCount,
        )

      }
    }

  }

}

@Composable
private fun ApplyAnimation(
  animatable: Animatable<Float, AnimationVector1D>,
  duration: Int = 1200,
  index: Int,
  lineCount: Int,
) {
  LaunchedEffect(key1 = Unit) {
    delay((duration / lineCount) * index.toLong())
    animatable.animateTo(
      targetValue = 1f,
      infiniteRepeatable(
        animation = tween(
          durationMillis = duration,
          easing = LinearEasing,
        ),
        repeatMode = RepeatMode.Restart,
      ),
    )
  }
}