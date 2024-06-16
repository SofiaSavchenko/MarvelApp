package com.example.core_ui.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

private const val DEVICE_MAX_WIDTH_COMPACT = 600
private const val DEVICE_MAX_WIDTH_MEDIUM = 840
private const val DEVICE_MAX_HEIGHT_COMPACT = 480
private const val DEVICE_MAX_HEIGHT_MEDIUM = 900

@Composable
fun isLandscape() = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

@Composable
fun getDeviceScreenConfiguration(): DeviceScreenConfiguration {
    val configuration = LocalConfiguration.current
    return DeviceScreenConfiguration(
        screenWidth = when {
            configuration.screenWidthDp < DEVICE_MAX_WIDTH_COMPACT -> DeviceScreenConfiguration.DeviceScreenSize.Compact
            configuration.screenWidthDp < DEVICE_MAX_WIDTH_MEDIUM -> DeviceScreenConfiguration.DeviceScreenSize.Medium
            else -> DeviceScreenConfiguration.DeviceScreenSize.Expanded
        },
        screenHeight = when {
            configuration.screenHeightDp < DEVICE_MAX_HEIGHT_COMPACT -> DeviceScreenConfiguration.DeviceScreenSize.Compact
            configuration.screenHeightDp < DEVICE_MAX_HEIGHT_MEDIUM -> DeviceScreenConfiguration.DeviceScreenSize.Medium
            else -> DeviceScreenConfiguration.DeviceScreenSize.Expanded
        },
    )
}
