package com.example.cmp_b.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.DoveGray
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles

@Composable
fun AppCustomRadioGroup(

    labelText: String,

    options: List<RadioOption>,

    selectedId: Int?,

    onOptionSelected: (Int) -> Unit
) {

    Column {

        // Label
        Text(
            text = labelText,
            style = TextStyles.InterMediumS,
            color = DoveGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Horizontal radio buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            options.forEach { option ->

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(

                        selected = option.id == selectedId,

                        onClick = {
                            if (!option.isDisabled) {
                                onOptionSelected(option.id)
                            }
                        },

                        enabled = !option.isDisabled,

                        colors = RadioButtonDefaults.colors(
                            selectedColor = Primary,
                            unselectedColor = Color.Gray
                        )
                    )

                    Text(
                        text = option.text,
                        style = TextStyles.InterMediumS,
                        color = DoveGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
data class RadioOption(
    val id: Int,
    val text: String,
    val isDisabled: Boolean = false
)