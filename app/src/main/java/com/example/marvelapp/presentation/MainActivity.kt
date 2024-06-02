package com.example.marvelapp.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import com.example.core_ui.theme.MarvelAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        if (Build.VERSION.SDK_INT > 32) {
            if (!shouldShowRequestPermissionRationale("112")) {
                getNotificationPermission();
            }
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            Log.e("TAG", "Token: $token")
        })

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MarvelAppTheme {

                ApplySystemBarColors()
                AppNavigation()
            }
        }
    }

    private fun getNotificationPermission() {
        try {
            if (Build.VERSION.SDK_INT > 32) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } catch (e: Exception) {
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
}

@Composable
private fun ApplySystemBarColors() {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
        systemUiController.setNavigationBarColor(color = Color.Transparent)
    }
}
