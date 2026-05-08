package com.example.cmp_b.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.Alto
import com.example.cmp_b.ui.theme.DoveGray
import com.example.cmp_b.ui.theme.MineShaft
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles

@Composable
fun AppOtpBoxes(
    value: String,
    onValueChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Enter OTP", style = TextStyles.InterBoldS, color = DoveGray)
        Spacer(modifier = Modifier.height(12.dp))
        BasicTextField(
            value = value,
            onValueChange = {
                if (it.length <= 4) {
                    onValueChange(it)
                    if (it.length == 4) keyboardController?.hide()
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    repeat(4) { index ->
                        val char = value.getOrNull(index)?.toString()
                        val isFocused = index == value.length
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .border(1.dp, if (isFocused) Primary else Alto, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char ?: "",
                                style = TextStyles.InterMediumXL,
                                color = if (isFocused) Primary else MineShaft
                            )
                        }
                    }
                }
            }
        )
    }
}