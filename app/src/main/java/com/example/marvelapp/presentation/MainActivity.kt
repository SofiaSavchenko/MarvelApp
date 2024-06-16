package com.example.marvelapp.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.core_ui.theme.MarvelAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.Modifier
import com.example.marvelapp.utils.NotificationConstants.Companion.TOPIC

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupFirebase()
        setupNotificationPermission()
        setupSystemBarColors()
        setContent {
            SetupUI()
        }
    }

    private fun setupFirebase() {
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.e("TAG", "Token: $token")
            }
        }
    }

    private fun setupNotificationPermission() {
        if (Build.VERSION.SDK_INT > 32 && !shouldShowRequestPermissionRationale("112")) {
            getNotificationPermission()
        }
    }

    private fun getNotificationPermission() {
        try {
            if (Build.VERSION.SDK_INT > 32) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } catch (_: Exception) {
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("TAG", "Status: granted")
            } else {
                Log.d("TAG", "Status: denied")
            }
        }

    private fun setupSystemBarColors() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    @Composable
    private fun SetupUI() {
        MarvelAppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.secondary
            ) {
                ApplySystemBarColors()
                AppNavigation()
            }
        }
    }

    @Composable
    private fun ApplySystemBarColors() {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(color = Color.Transparent)
            systemUiController.setNavigationBarColor(color = Color.Transparent)
        }
    }
}
