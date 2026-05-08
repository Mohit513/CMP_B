package com.example.cmp_b.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.Alto
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.SilverChalice
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.Zumthor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppCommonChipSelector(
    modifier: Modifier = Modifier,
    labelText: String = "",
    options: List<String>,
    selectedOption: String,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    onOptionSelected: (String) -> Unit,
) {

    Column(
        modifier = modifier.fillMaxWidth().padding(1.dp)
    ) {

        Text(
            labelText, style = TextStyles.InterRegularS, modifier = Modifier.padding(bottom = 2.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            options.forEach { option ->

                val isSelected = option == selectedOption

                FilterChip(
                    modifier = Modifier.height(40.dp),
                    selected = isSelected,
                    onClick = { onOptionSelected(option) },
                    label = {
                        Text(
                            text = option, style = if (isSelected) TextStyles.InterBoldXS
                            else TextStyles.InterRegularXS
                        )
                    },
                    shape = shape,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Zumthor,
                        selectedLabelColor = Primary,
                        containerColor = BackgroundLight,
                        labelColor = Color.Black
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = SilverChalice,
                        selectedBorderColor = Primary,
                        borderWidth = 1.dp,
                        selectedBorderWidth = 1.dp,
                        enabled = true,
                        selected = isSelected,
                        disabledBorderColor = Zumthor,
                        disabledSelectedBorderColor = Alto
                    )
                )
            }
        }
    }
}