package com.example.cmp_b.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.GalleryColor
import com.example.cmp_b.ui.theme.MineShaft
import com.example.cmp_b.ui.theme.SilverChalice
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.Tundora


@Composable
fun AppLabelWithContainer(
    modifier: Modifier = Modifier,
    labelText: String,
    description: String,
    labelColor: Color = MineShaft,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Text(
        text = labelText,
        style = TextStyles.InterRegularS,
        color = labelColor,
        modifier = Modifier.padding(bottom = 6.dp)
    )
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 500) {
                onValueChange(it)
            }
        },
        placeholder = {
            Text(
                text = description,
                style = TextStyles.InterRegularXS,
                color = SilverChalice
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        textStyle = TextStyles.InterRegularS,
        shape = RoundedCornerShape(12.dp),
        maxLines = 5,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GalleryColor,
            unfocusedBorderColor = GalleryColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorCursorColor = Tundora,
            cursorColor = Tundora,
        ),

        )
}