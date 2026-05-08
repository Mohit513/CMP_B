package com.example.cmp_b.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles

@Composable
fun AppCustomCardWithLabel(
    cardLabel: String = "",
    cardLabelColor: Color = Primary,
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    cornerRadius: Dp = 12.dp,
    elevation: Dp = 1.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()
        .wrapContentHeight()) {
        Text(
            text = cardLabel,
            style = TextStyles.InterSemiBoldS,
            color = cardLabelColor,
            modifier = Modifier.padding(padding)
        )
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(cornerRadius),
            elevation = CardDefaults.cardElevation(defaultElevation = elevation),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                content()
            }
        }
    }
}