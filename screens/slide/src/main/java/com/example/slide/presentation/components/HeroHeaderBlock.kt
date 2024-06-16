package com.example.slide.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.core_ui.theme.MarvelAppTheme
import com.example.core_ui.utils.DeviceScreenConfiguration
import com.example.core_ui.utils.getDeviceScreenConfiguration
import com.example.core_ui.utils.isLandscape
import com.example.slide.R
import com.example.slide.presentation.utils.compactPaddingLandscape
import com.example.slide.presentation.utils.compactPaddingPortrait
import com.example.slide.presentation.utils.compactSpacerLandscape
import com.example.slide.presentation.utils.compactSpacerPortrait
import com.example.slide.presentation.utils.expandedPaddingLandscape
import com.example.slide.presentation.utils.expandedPaddingPortrait
import com.example.slide.presentation.utils.expandedSpacerLandscape
import com.example.slide.presentation.utils.expandedSpacerPortrait
import com.example.slide.presentation.utils.mediumPaddingLandscape
import com.example.slide.presentation.utils.mediumPaddingPortrait
import com.example.slide.presentation.utils.mediumSpacerLandscape
import com.example.slide.presentation.utils.mediumSpacerPortrait

@Composable
fun HeroHeaderBlock() {

    val landscapePadding = when (getDeviceScreenConfiguration().screenWidth) {
        DeviceScreenConfiguration.DeviceScreenSize.Compact -> compactPaddingLandscape
        DeviceScreenConfiguration.DeviceScreenSize.Expanded -> expandedPaddingLandscape
        DeviceScreenConfiguration.DeviceScreenSize.Medium -> mediumPaddingLandscape
    }

    val portraitPadding = when (getDeviceScreenConfiguration().screenHeight) {
        DeviceScreenConfiguration.DeviceScreenSize.Compact -> compactPaddingPortrait
        DeviceScreenConfiguration.DeviceScreenSize.Expanded -> expandedPaddingPortrait
        DeviceScreenConfiguration.DeviceScreenSize.Medium -> mediumPaddingPortrait
    }

    val portraitSpacer = when (getDeviceScreenConfiguration().screenHeight) {
        DeviceScreenConfiguration.DeviceScreenSize.Compact -> compactSpacerPortrait
        DeviceScreenConfiguration.DeviceScreenSize.Expanded -> expandedSpacerPortrait
        DeviceScreenConfiguration.DeviceScreenSize.Medium -> mediumSpacerPortrait
    }

    val landscapeSpacer = when (getDeviceScreenConfiguration().screenHeight) {
        DeviceScreenConfiguration.DeviceScreenSize.Compact -> compactSpacerLandscape
        DeviceScreenConfiguration.DeviceScreenSize.Expanded -> expandedSpacerLandscape
        DeviceScreenConfiguration.DeviceScreenSize.Medium -> mediumSpacerLandscape
    }

    Column(
        when (isLandscape()) {
            true -> Modifier.padding(landscapePadding)
            false -> Modifier.padding(portraitPadding)
        },

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoadLogoImage()

        when (isLandscape()) {
            true -> Spacer(modifier = Modifier.height(landscapeSpacer))
            false -> Spacer(modifier = Modifier.height(portraitSpacer))
        }

        Text(
            text = stringResource(R.string.header_title),
            style = MarvelAppTheme.TextStyle.ExtraBold_28
        )
    }
}

@Composable
private fun LoadLogoImage() {

    AsyncImage(
        model = "https://iili.io/JMnuvbp.png",
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .width(dimensionResource(R.dimen.size_logoImageWidth))
            .height(dimensionResource(R.dimen.size_logoImageHeight))
    )
}

@Preview
@Composable
fun PreviewHeroHeader() {
    MarvelAppTheme {

        Surface(color = MaterialTheme.colorScheme.secondary) {

            HeroHeaderBlock()
        }
    }

}
