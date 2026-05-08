package com.example.cmp_b.ui.components

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.cmp_b.ui.theme.Silver
import java.util.Calendar

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
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Box(modifier = modifier.fillMaxWidth()) {
        AppTextFieldWithLabel(
            modifier = Modifier.fillMaxWidth().clickable {
                if (!readOnly) {
                    val dialog = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            onDateSelected("$dayOfMonth/${month + 1}/$year")
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    if (isTodayMax) {
                        dialog.datePicker.maxDate = System.currentTimeMillis()
                    }
                    dialog.show()
                }
            },
            labelText = labelText,
            hint = hint,
            value = selectedDate,
            onValueChange = {},
            isError = isError,
            readOnly = true
        )
    }
}
