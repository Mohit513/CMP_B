package com.example.cmp_b.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Primary = Color(0xFF003F88)
val PrimaryVariant = Color(0xFF3700B3)
val SecondaryColor = Color(0xFF03DAC6)
val BgSurface = Color(0xFFF5F7FA)
val BackgroundDark = Color(0xFF121212)
val SurfaceDark = Color(0xFF121212)
val ErrorColor = Color(0xFFB00020)
val BackgroundLight = Color(0xFFFFFFFF)
val SurfaceLight = Color(0xFFFFFFFF)

// Splash Screen Colors
val SplashGradientStart = Color(0xFF00A8A8)
val SplashGradientEnd = Color(0xFF007373)
val SplashGlowColor = Color(0xFFE6FFFF)
val SplashWaveColor = Color(0x66FFFFFF)
val SplashTextPrimary = Color.White
val SplashTextSecondary = Color.White.copy(alpha = 0.8f)

val Bright_red = Color(0xFFA60000)
val DoveGray = Color(0xFF616161)
val Silver = Color(0xFFC9C9C9)
val SilverChalice = Color(0xFF9E9E9E)
val Alto = Color(0xFFD6D6D6)
val BoldTextColor = Color(0xFF212121)
val Tundora = Color(0xFF454545)
val CodGray = Color(0xFF131313)
val CatskillWhite = Color(0xFFf5f7fa)
val MineShaft = Color(0xFF212121)
val CuriousBlue = Color(0xFF1E88E5)
val HawkesBlue = Color(0xFFE3F2FD)
val GinFizz = Color(0xFFFFF8E1)
val Clementine = Color(0xFFEF6C00)
val Boulder = Color(0xFF757575)
val GalleryColor = Color(0xFFEEEEEE)
val AeroWhite = Color(0xFFF5F7FA)
val Parsely = Color(0xFF1B5E20)
val Harp = Color(0xFFE8F5E9)
val Seashell = Color(0xFFEEEEEE)
val SilverShell = Color(0xFFBDBDBD)
val Geyser = Color(0xFFE0E0E0)
val Jaguar = Color(0xFF767676)
val Alto_5 = Color(0xFFE0E0E0)
val AliceBlue_25 = Color(0xFFF3FAFF)



// Dashboard-specific colors
val Alabaster = Color(0xFFF8F8F8)
val Congress_blue = Color(0xFF004AAD)
val CreamBrulle = Color(0xFFF5DEB3)
val Havelock_blue = Color(0xFF4A90E2)
val Piper = Color(0xFFB86A2B)
val SeaSellPeach = Color(0xFFF5E6DC)

object AppGradients {
    fun lightPrimaryBackground(color: Color): androidx.compose.ui.graphics.Brush {
        return androidx.compose.ui.graphics.Brush.verticalGradient(
            colors = listOf(color.copy(alpha = 0.12f), Color.White)
        )
    }
}
