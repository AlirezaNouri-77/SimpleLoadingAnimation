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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ThreeDotFading(
  modifier: Modifier = Modifier,
  size: Dp = 40.dp,
  color: Color = Color.Black,
  circleRadius: Float = 10f,
) {

  val countCircle = 3

  val animate = remember {
    Array(countCircle) {
      Animatable(0f)
    }
  }

  (0 until countCircle).forEachIndexed { index, _ ->
    ApplyAnimation(animate[index], index)
  }

  Box(
    modifier = modifier.size(size),
    contentAlignment = Alignment.Center,
  ) {
    Canvas(
      modifier = Modifier
        .matchParentSize(),
    ) {

      (0 until countCircle).forEachIndexed { index, _ ->

        val center = when (index) {
          0 -> Offset(x = this.center.x - (circleRadius * 2.5f), y = this.center.y)
          1 -> this.center
          2 -> Offset(x = this.center.x + (circleRadius * 2.5f), y = this.center.y)
          else -> Offset.Zero
        }

        drawCircle(
          color = color,
          center = center,
          alpha = animate[index].value,
          radius = circleRadius
        )
      }

    }
  }

}

@Composable
private fun ApplyAnimation(animatable: Animatable<Float, AnimationVector1D>, index: Int) {
  LaunchedEffect(key1 = Unit) {
    launch(Dispatchers.Main.immediate) {
      delay(700 / 3 * index.toLong())
      animatable.animateTo(
        targetValue = 1f,
        infiniteRepeatable(
          animation = tween(
            durationMillis = 700,
            easing = LinearEasing
          ),
          repeatMode = RepeatMode.Reverse,
        ),
      )
    }
  }
}