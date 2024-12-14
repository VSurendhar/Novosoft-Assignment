package com.noasoftsolutions.machineCoding.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.noasoftsolutions.machineCoding.MainActivity
import com.noasoftsolutions.machineCoding.R
import com.noasoftsolutions.machineCoding.ui.theme.NoaSoftSolutionsTheme

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoaSoftSolutionsTheme {
                val splashDuration = 2000L // 2 seconds delay

                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(splashDuration)
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color(0xFF61CE70), Color(0xFF42794A)),
                                center = Offset(0.5f, 0f),
                                radius = 1000f
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.splashscreen),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
            }
        }
    }
}
