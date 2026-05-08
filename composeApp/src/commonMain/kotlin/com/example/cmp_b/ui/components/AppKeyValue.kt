package com.example.cmp_b.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.Boulder
import com.example.cmp_b.ui.theme.MineShaft_5
import com.example.cmp_b.ui.theme.TextStyles


@Composable
fun AppKeyValue(
    modifier: Modifier = Modifier,
    key: String = "Key",
    keyColor: Color = MineShaft_5,
    readOnly: Boolean = false,
    value: String = "Value",
    maxLines: Int = 1,
    align: TextAlign = TextAlign.Start
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp)
    ) {
        Text(
            key,
            style = TextStyles.InterMediumS,
            color = if (readOnly) MineShaft_5 else keyColor,
            textAlign = align,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = value,
            style = TextStyles.InterMediumS,
            color = Boulder,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            textAlign = align
        )
    }
}