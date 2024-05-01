package com.shermanrex.animationloading.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DashCircleLoading(
  modifier: Modifier = Modifier,
  size: Dp = 20.dp,
) {

  val infiniteTransition = rememberInfiniteTransition(label = "")

  val dashAnimate = infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 2300, easing = LinearEasing),
      repeatMode = RepeatMode.Restart,
    ),
    label = ""
  ).value

  val lineAnimate = infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 3000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart,
    ),
    label = ""
  ).value

  Box(
    modifier = modifier.size(size),
    contentAlignment = Alignment.Center,
  ) {

    Canvas(modifier = Modifier.matchParentSize()) {

      val dash = 0.4f
      val dashGap = 0.9f

      val circumferenceCircle = 2 * Math.PI * (this.size.width / 2)
      val dashCapSize = circumferenceCircle / 9

      drawArc(
        color = Color.Black,
        startAngle = dashAnimate,
        sweepAngle = 359f,
        useCenter = false,
        style = Stroke(
          width = 15f,
          cap = StrokeCap.Round,
          pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(
              (dashCapSize * dash).toFloat(),
              (dashCapSize * dashGap).toFloat()
            ),
          )
        ),
      )
      drawArc(
        color = Color.Black,
        useCenter = false,
        startAngle = -lineAnimate,
        sweepAngle = 200f,
        style = Stroke(
          width = 15f,
          cap = StrokeCap.Round,
        ),
      )
    }

  }

}