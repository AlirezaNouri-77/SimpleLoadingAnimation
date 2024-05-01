package com.shermanrex.animationloading.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
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

@Composable
fun ThreeDotScaling(
  modifier: Modifier = Modifier,
  size: Dp = 40.dp,
  color: Color = Color.Black,
  circleRadius: Float = 10f,
) {

  val circleCount = 3

  val animate = remember {
    Array(circleCount) {
      Animatable(0.3f)
    }
  }

  (1..circleCount).forEachIndexed { index, _ ->
    ApplyAnimation(animatable = animate[index], index = index, circleCount = circleCount)
  }

  Box(
    modifier = modifier.size(size),
    contentAlignment = Alignment.Center,
  ) {
    Canvas(
      modifier = Modifier
        .matchParentSize(),
    ) {

      (1..circleCount).forEachIndexed { index, _ ->

        val center = when (index) {
          0 -> Offset(x = this.center.x - (circleRadius * 2.5f), y = this.center.y)
          1 -> this.center
          2 -> Offset(x = this.center.x + (circleRadius * 2.5f), y = this.center.y)
          else -> Offset.Zero
        }

        drawCircle(
          color = color,
          center = center,
          radius = circleRadius * animate[index].value
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
  circleCount: Int,
) {
  LaunchedEffect(key1 = Unit) {
    delay(duration / circleCount * index.toLong())
    animatable.animateTo(
      targetValue = 1f,
      infiniteRepeatable(
        animation = tween(
          durationMillis = duration,
          easing = FastOutSlowInEasing
        ),
        repeatMode = RepeatMode.Reverse,
      ),
    )
  }
}