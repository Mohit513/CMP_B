package com.example.cmp_b.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.Alto
import com.example.cmp_b.ui.theme.GalleryColor
import com.example.cmp_b.ui.theme.MineShaft
import com.example.cmp_b.ui.theme.Silver
import com.example.cmp_b.ui.theme.SilverChalice
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.Tundora
import kotlin.collections.copy

@Composable
fun AppTextFieldWithLabel(
    modifier: Modifier = Modifier,
    labelText: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    readOnly: Boolean = false,
    borderColor: Color = Silver,
    keyboardType: KeyboardType = KeyboardType.Number,
    visualTransformation: VisualTransformation = VisualTransformation.None,
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
            textStyle = TextStyles.InterRegularS.copy(
                color = if (readOnly) SilverChalice else MineShaft
            ),
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyles.InterRegularS,
                    color = SilverChalice,
                    overflow = TextOverflow.Ellipsis
                )
            },
            singleLine = true,
            isError = isError,
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp).border(
                    width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp)
                ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                disabledBorderColor = borderColor,
                errorBorderColor = if (isError) MaterialTheme.colorScheme.error else borderColor,
                errorCursorColor = Tundora,
                cursorColor = Tundora,
                focusedLabelColor = Alto,
                focusedContainerColor = if (readOnly) GalleryColor else Color.White,
                unfocusedContainerColor = if (readOnly) GalleryColor else Color.White,
                disabledContainerColor = if (readOnly) GalleryColor else Color.White,
                errorContainerColor = if (readOnly) GalleryColor else Color.White
            ),

            )
    }
}