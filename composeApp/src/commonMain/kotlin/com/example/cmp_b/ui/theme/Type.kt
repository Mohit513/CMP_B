package com.example.cmp_b.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Note: Custom fonts (Inter) were referenced in the original snippet but not found in the project.
// Using FontFamily.Default for now.
val Inter = FontFamily.Default

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color(0xFF0B0C0E)
    ),
    displayMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)

object TextStyles {
    // ========== Light ==========
    val InterLightXXL = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Light, fontSize = 22.sp)
    val InterLightXL  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Light, fontSize = 20.sp)
    val InterLightL   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Light, fontSize = 18.sp)
    val InterLightM   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Light, fontSize = 16.sp)
    val InterLightS   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Light, fontSize = 14.sp)
    val InterLightXS  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Light, fontSize = 12.sp)
    val InterLightXXS = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Light, fontSize = 10.sp)

    // ========== Regular ==========
    val InterRegularXXL = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Normal, fontSize = 22.sp)
    val InterRegularXL  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Normal, fontSize = 20.sp)
    val InterRegularL   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Normal, fontSize = 18.sp)
    val InterRegularM   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Normal, fontSize = 16.sp)
    val InterRegularS   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Normal, fontSize = 14.sp)
    val InterRegularXS  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Normal, fontSize = 12.sp)
    val InterRegularXXS = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Normal, fontSize = 10.sp)

    // ========== Medium ==========
    val InterMediumXXL = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 22.sp)
    val InterMediumXL  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 20.sp)
    val InterMediumL   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 18.sp)
    val InterMediumM   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 16.sp)
    val InterMediumS   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    val InterMediumXS  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 12.sp)
    val InterMediumXXS = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 10.sp)

    // ========== SemiBold ==========
    val InterSemiBoldXXL = TextStyle(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
    val InterSemiBoldXL  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
    val InterSemiBoldL   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
    val InterSemiBoldM   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    val InterSemiBoldS   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    val InterSemiBoldXS  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
    val InterSemiBoldXXS = TextStyle(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 10.sp)

    // ========== Bold ==========
    val InterBoldXXL = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 22.sp)
    val InterBoldXL  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    val InterBoldL   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    val InterBoldM   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    val InterBoldS   = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    val InterBoldXS  = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    val InterBoldXXS = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 10.sp)
}
