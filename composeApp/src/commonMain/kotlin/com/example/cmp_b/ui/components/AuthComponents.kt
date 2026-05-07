package com.example.cmp_b.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cmp_b.ui.theme.Alto
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.DoveGray
import com.example.cmp_b.ui.theme.MineShaft
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.Silver
import com.example.cmp_b.ui.theme.SilverChalice
import com.example.cmp_b.ui.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AuthHeaderImage(
    imageRes: DrawableResource,
    title: String,
    subtitleContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 24.dp, vertical = 80.dp)
        ) {
            Text(
                text = title,
                style = TextStyles.InterSemiBoldXXL,
                color = BackgroundLight,
            )
            Spacer(modifier = Modifier.height(8.dp))
            subtitleContent()
        }
    }
}

@Composable
fun AppTextFieldWithLabel(
    modifier: Modifier = Modifier,
    labelText: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Number,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            labelText,
            style = TextStyles.InterRegularS,
            color = if (readOnly) SilverChalice else MineShaft
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = hint, style = TextStyles.InterRegularS, color = Silver, overflow = TextOverflow.Ellipsis)
            },
            singleLine = true,
            isError = isError,
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Alto
            )
        )
    }
}

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

@Composable
fun AppMultipleButtons(
    modifier: Modifier = Modifier,
    firstButtonText: String,
    secondButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedButton(
            onClick = onFirstButtonClick,
            modifier = Modifier.weight(1f).height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(firstButtonText)
        }
        Button(
            onClick = onSecondButtonClick,
            modifier = Modifier.weight(1f).height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary)
        ) {
            Text(secondButtonText)
        }
    }
}

@Composable
fun AppCustomButton(
    modifier: Modifier = Modifier,
    firstButtonText: String,
    onFirstButtonClick: () -> Unit
) {
    Button(
        onClick = onFirstButtonClick,
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Primary)
    ) {
        Text(firstButtonText)
    }
}
