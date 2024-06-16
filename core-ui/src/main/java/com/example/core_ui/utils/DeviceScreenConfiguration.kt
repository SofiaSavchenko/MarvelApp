package com.example.core_ui.utils

data class DeviceScreenConfiguration(
    val screenWidth: DeviceScreenSize,
    val screenHeight: DeviceScreenSize,
) {
    sealed class DeviceScreenSize {
        data object Compact : DeviceScreenSize()
        data object Medium : DeviceScreenSize()
        data object Expanded : DeviceScreenSize()
    }
}
