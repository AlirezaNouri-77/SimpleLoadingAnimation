package com.shermanrex.animationloading.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FadeCircleLoading(
  modifier: Modifier = Modifier,
  size: Dp = 20.dp,
  color: Color = Color.Black,
) {

  val repeatAnimate = rememberInfiniteTransition(label = "")
  val animateFloat = repeatAnimate.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart,
    ),
    label = ""
  ).value

  Box(
    modifier = modifier
      .size(size),
    contentAlignment = Alignment.Center,
  ) {
    Canvas(
      modifier = Modifier
        .fillMaxSize()
        .graphicsLayer {
          this.rotationZ = animateFloat
        },
    ) {

      drawArc(
        brush = Brush.sweepGradient(
          colorStops = arrayOf(
            0f to color,
            0.65f to color,
            1f to Color.Transparent,
          ),
        ),
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
          width = this.size.width / 6,
        ),
      )

    }
  }

}