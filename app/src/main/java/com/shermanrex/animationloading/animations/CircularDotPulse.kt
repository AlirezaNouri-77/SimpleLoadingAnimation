package com.shermanrex.animationloading.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularDotPulse(
  circleColor: Color = Color.Black,
) {

  val size: Dp = 50.dp

  val animate = remember {
    Array(3) {
      Animatable(2f)
    }
  }

  (1..3).forEachIndexed { index, _ ->
    ApplyAnimation(animatable = animate[index], index = index)
  }

  Box(
    modifier = Modifier
      .size(size),
    contentAlignment = Alignment.Center,
  ) {
    Canvas(
      modifier = Modifier
        .fillMaxSize(),
    ) {

      (1..3).forEachIndexed { outIndex, first ->

        val circleCount = 10

        (1..circleCount).forEachIndexed { index, second ->

          val angel = Math.PI / (circleCount / 2) * second

          val startX =
            this.size.width / 2 + sin(angel) * ((this.size.width % 10) + first * 18 + animate[outIndex].value)
          val endX =
            this.size.width / 2 + cos(angel) * ((this.size.width % 10) + first * 18 + animate[outIndex].value)

          drawCircle(
            color = circleColor,
            center = Offset(x = startX.toFloat(), y = endX.toFloat()),
            style = if (outIndex == 0) {
              Stroke(2f, pathEffect = PathEffect.cornerPathEffect(10f))
            } else Fill,
            radius = 5f,
          )

        }
      }
      drawCircle(
        color = circleColor,
        center = Offset(x = this.size.width / 2, y = this.size.height / 2),
        radius = 5f + animate[0].value / 2,
      )
    }
  }
}

@Composable
private fun ApplyAnimation(
  animatable: Animatable<Float, AnimationVector1D>,
  duration: Int = 1200,
  index: Int,
) {
  LaunchedEffect(key1 = Unit) {

    delay((duration / 3) * index.toLong())

    animatable.animateTo(
      targetValue = 8f,
      infiniteRepeatable(
        animation = tween(
          durationMillis = duration,
          easing = FastOutSlowInEasing,
        ),
        repeatMode = RepeatMode.Reverse,
      ),
    )
  }
}

