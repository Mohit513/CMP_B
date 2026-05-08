package com.example.cmp_b.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_arrow_down
import com.example.cmp_b.ui.theme.Alto
import com.example.cmp_b.ui.theme.GalleryColor
import com.example.cmp_b.ui.theme.MineShaft
import com.example.cmp_b.ui.theme.Silver
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.Tundora
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropdownWithLabel(
    modifier: Modifier = Modifier,
    labelText: String,
    hint: String,
    itemList: List<String>,
    selectedItem: String,
    onSelectedItem: (String) -> Unit,
    isError: Boolean = false,
    borderColor: Color = Silver,
    readOnly: Boolean = false,
    ) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = ""
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp)
    ) {

        //  Label
        Text(
            text = labelText,
            style = TextStyles.InterRegularS,
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedTextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                isError = isError,
                textStyle = TextStyles.InterRegularS.copy(
                    color =  MineShaft
                ),                placeholder = {
                    Text(
                        hint,
                        style = TextStyles.InterRegularS,
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_down),
                        contentDescription = "Dropdown Icon",
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(rotation)
                    )
                },
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
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .menuAnchor() // important
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 0.5.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(8.dp) )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                itemList.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                style = TextStyles.InterRegularS
                            )
                        },
                        onClick = {
                            onSelectedItem(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}