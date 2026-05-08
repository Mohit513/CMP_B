package com.example.cmp_b.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
actual fun AppDatePickerWithLabel(
    labelText: String,
    hint: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    isError: Boolean,
    borderColor: Color,
    readOnly: Boolean,
    isTodayMax: Boolean,
    modifier: Modifier
) {
    // Stub for iOS
    AppTextFieldWithLabel(
        modifier = modifier,
        labelText = labelText,
        hint = hint,
        value = selectedDate,
        onValueChange = {},
        isError = isError,
        readOnly = true
    )
}
