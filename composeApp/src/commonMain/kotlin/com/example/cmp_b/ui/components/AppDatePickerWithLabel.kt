package com.example.cmp_b.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cmp_b.ui.theme.Silver

@Composable
expect fun AppDatePickerWithLabel(
    labelText: String,
    hint: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    isError: Boolean = false,
    borderColor: Color = Silver,
    readOnly: Boolean = false,
    isTodayMax: Boolean = false,
    modifier: Modifier = Modifier,
)