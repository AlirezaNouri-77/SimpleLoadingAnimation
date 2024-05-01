package com.shermanrex.animationloading.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleLoading(
  modifier: Modifier = Modifier,
  size: Dp = 40.dp,
  strokeWidth: Float = 10f,
  strokeLength: Float = 55f,
  strokeColor: Color = Color.Black,
  holderColor: Color = strokeColor,
) {

  Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animateFloat = infiniteTransition.animateFloat(
      initialValue = 0f,
      targetValue = 360f,
      animationSpec = infiniteRepeatable(
        animation = tween(durationMillis = 1000, easing = LinearEasing),
        repeatMode = RepeatMode.Restart,
      ),
      label = ""
    ).value

    Canvas(
      modifier = Modifier
        .matchParentSize()
        .padding(10.dp)
    ) {

      drawArc(
        color = holderColor.copy(0.5f),
        useCenter = false,
        startAngle = 0f,
        sweepAngle = 360f,
        topLeft = Offset(x = strokeWidth / 2.toFloat(), y = strokeWidth / 2.toFloat()),
        style = Stroke(
          width = strokeWidth,
          cap = StrokeCap.Round,
        ),
        alpha = 0.25f,
      )

      drawArc(
        color = holderColor,
        useCenter = false,
        startAngle = animateFloat,
        sweepAngle = strokeLength,
        topLeft = Offset(x = strokeWidth / 2.toFloat(), y = strokeWidth / 2.toFloat()),
        style = Stroke(
          width = strokeWidth,
          cap = StrokeCap.Round,
        ),
      )


    }

  }
}