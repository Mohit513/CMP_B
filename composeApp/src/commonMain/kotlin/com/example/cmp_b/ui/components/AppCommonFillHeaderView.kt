package com.example.cmp_b.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.Congress_blue
import com.example.cmp_b.ui.theme.Silver
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.Zumthor


@Composable
fun AppCommonFillHeaderView (
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    containerColor: Color = Zumthor,
    borderColor: Color = Silver,
    maxLength: Int? = null,
    headText:String
    ){
    Box(modifier = modifier.fillMaxWidth().height(50.dp).background(containerColor, shape = RoundedCornerShape(12.dp))){
        Text(text = headText, style = TextStyles.InterSemiBoldS, color = Congress_blue, textAlign = TextAlign.Center,modifier = Modifier.align(
            Alignment.CenterStart)
            .padding(horizontal = 8.dp))
    }
}