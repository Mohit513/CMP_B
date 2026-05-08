package com.example.cmp_b.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles.InterBoldS

@Composable
fun AppMultipleButtons(
    modifier: Modifier = Modifier,
    firstButtonText: String,
    secondButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
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

/** app custom single button **/
@Composable
fun AppCustomButton(
    modifier: Modifier,
    firstButtonText: String = "Login",
    onFirstButtonClick: () -> Unit,
    bgColor: Color = Primary,
    contentColor : Color = BackgroundLight
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        ElevatedButton(
            onClick = onFirstButtonClick,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                contentColor = contentColor
            )
        ) {
            Text(
                text = firstButtonText,
                style = InterBoldS,
                textAlign = TextAlign.Center
            )
        }
    }
}