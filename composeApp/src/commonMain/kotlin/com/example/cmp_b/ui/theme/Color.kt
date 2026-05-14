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
val Azure = Color(0xFF3454AC)
val Mercury = Color(0xFFE2E2E2)
val Zumthor = Color(0xFFE9F3FF)

// Dashboard-specific colors
val Alabaster = Color(0xFFF8F8F8)
val Congress_blue = Color(0xFF004AAD)
val CreamBrulle = Color(0xFFF5DEB3)
val Havelock_blue = Color(0xFF4A90E2)
val Piper = Color(0xFFB86A2B)
val SeaSellPeach = Color(0xFFF5E6DC)
val MineShaft_5 = Color(0xFF242424)
val Your_pink = Color(0xFFFFC2C2)

val hotSinnamon = Color(0xFFD56B1B)
val persianRed = Color(0xFFD32F2F)
val DustyGray = Color(0xFF999999)

val Cloud_Burst = Color(0xFF1C274C)
val Gallery = Color(0xFFEDEDED)
val Gallery_5 = Color(0xFFECECEC)
val FrostedMint = Color(0xFFE4FFF3)
val Pippin = Color(0xFFFFE4E4)
val Pippin_15 = Color(0xFFFFE7E7)

val FunGreen = Color(0xFF05603A)
val OldBrick = Color(0xFF912018)
val Melrose = Color(0xFF97ABFF)
val ToriaBay = Color(0xFF123597)
val Solitude = Color(0xFFE5F4FF)
val Kaitoke_Green = Color(0xFF00552C)

val Concrete = Color(0xFFF3F3F3)
val AliceBLue = Color(0xFFEEF8FF)
val Marzipan = Color(0xFFFAE69F)
val HawkesBlue_15 = Color(0xFFF0F6FE)
val Turquoise = Color(0xFF38D5BB)
val Curious_blue_15 = Color(0xFF3898D5)
val Cerenade = Color(0xFFFFF2E7)
val HintOfGreen = Color(0xFFE8FFE7)
val BrightRed = Color(0xFFB10F00)
val FunGreen_50 = Color(0xFF008B3E)
val IndoShine = Color(0xFFC96B00)
val JapaneseLauren = Color(0xFF079100)
val RedBerry = Color(0xFF910000)

val MangoTango = Color(0xFFD88501)
val WildSand = Color(0xFFF5F5F5)
val Alice_blue_50 = Color(0xFFF2F8FF)

object AppGradients {
    fun lightPrimaryBackground(color: Color = Havelock_blue): androidx.compose.ui.graphics.Brush {
        return androidx.compose.ui.graphics.Brush.verticalGradient(
            colors = listOf( Color.White,color.copy(alpha = 0.03f),)
        )
    }
}
