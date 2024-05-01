package com.shermanrex.animationloading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shermanrex.animationloading.animations.CircleBounce
import com.shermanrex.animationloading.animations.CircleLoading
import com.shermanrex.animationloading.animations.CirclePulseFade
import com.shermanrex.animationloading.animations.CircularDotPulse
import com.shermanrex.animationloading.animations.CircularDotScaleFade
import com.shermanrex.animationloading.animations.CircularDotScaleIn
import com.shermanrex.animationloading.animations.CircularLineFade
import com.shermanrex.animationloading.animations.DashCircleLoading
import com.shermanrex.animationloading.animations.FadeCircleLoading
import com.shermanrex.animationloading.animations.ThreeDotFading
import com.shermanrex.animationloading.animations.ThreeDotScaling

@Composable
fun PreviewListLoading() {
  LazyColumn(
    Modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    item {
      PreviewItem(name = "CircleBounce") {
        CircleBounce()
      }
    }
    item {
      PreviewItem(name = "CircularLineFade") {
        CircularLineFade()
      }
    }
    item {
      PreviewItem(name = "CircleLoading") {
        CircleLoading()
      }
    }
    item {
      PreviewItem(name = "CircularCircleScaleFade") {
        CircularDotScaleFade()
      }
    }
    item {
      PreviewItem(name = "CirclePulseFade") {
        CirclePulseFade()
      }
    }
    item {
      PreviewItem(name = "FadeCircleLoading") {
        FadeCircleLoading()
      }
    }
    item {
      PreviewItem(name = "DashCircleLoading") {
        DashCircleLoading()
      }
    }
    item {
      PreviewItem(name = "CircularDotScaleIn") {
        CircularDotScaleIn()
      }
    }
    item {
      PreviewItem(name = "ThreeDotFading") {
        ThreeDotFading()
      }
    }
    item {
      PreviewItem(name = "ThreeDotScaling") {
        ThreeDotScaling()
      }
    }
    item {
      PreviewItem(name = "CircularDotPulse") {
        CircularDotPulse()
      }
    }
  }
}

@Composable
private fun PreviewItem(
  name: String,
  loading: @Composable () -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
  ) {
    loading()
    Spacer(modifier = Modifier.width(20.dp))
    Text(text = name)
  }
}
