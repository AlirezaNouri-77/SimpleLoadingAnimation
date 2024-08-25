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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircleBounce(
  modifier: Modifier = Modifier,
  circleCount: Int = 12,
  circleColor: Color = Color.Black,
  size: Dp = 30.dp,
) {

  val animate = remember {
    Array(circleCount) {
      Animatable(4.5f)
    }
  }
  (1..circleCount).forEachIndexed { index, _ ->
    ApplyAnimation(animatable = animate[index], index = index, lineCount = circleCount)
  }

  Box(
    modifier = modifier.size(size),
    contentAlignment = Alignment.Center,
  ) {
    Canvas(
      modifier = Modifier
        .matchParentSize(),
    ) {

      (1..circleCount).forEachIndexed { index, i ->

        val angel = Math.PI / (circleCount / 2) * i - circleCount
        val startX =
          this.size.width / 2 + sin(angel) * this.size.width / animate[index].value
        val endX =
          this.size.width / 2 + cos(angel) * this.size.width / animate[index].value

        drawCircle(
          color = circleColor,
          center = Offset(x = startX.toFloat(), y = endX.toFloat()),
          radius = this.size.width / circleCount - 3f,
        )

      }

    }

  }

}

@Composable
private fun ApplyAnimation(
  animatable: Animatable<Float, AnimationVector1D>,
  duration: Int = 800,
  index: Int,
  lineCount: Int,
) {
  LaunchedEffect(key1 = Unit) {
    delay(duration / (lineCount / 2) * index.toLong())
    animatable.animateTo(
      targetValue = 2.5f,
      infiniteRepeatable(
        animation = tween(
          durationMillis = duration,
          easing = LinearEasing,
        ),
        repeatMode = RepeatMode.Reverse,
      ),
    )
  }
}