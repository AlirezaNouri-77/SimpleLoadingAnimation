package com.shermanrex.animationloading

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shermanrex.animationloading.ui.theme.AnimationLoaingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AnimationLoaingTheme {
        // A surface container using the 'background' color from the theme
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          PreviewListLoading()
        }
      }
    }
  }
}
