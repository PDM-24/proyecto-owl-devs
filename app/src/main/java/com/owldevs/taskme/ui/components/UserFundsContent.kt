package com.owldevs.taskme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.owldevs.taskme.R


@Composable
fun UserFundsContent(){
    Box(
        modifier = Modifier
            .topAndBottomBorderC(borderSize = 3.dp, color = Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_dollarsign),
                    contentDescription = "dollar sign"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Jhon Doe", fontWeight = FontWeight.Bold)
                    Text(text = "Fontaneria")
                    Text(text = "DD/MM/YY")
                }
            }
            Text(
                text = "+$0.00",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )

        }
    }
}

fun Modifier.topAndBottomBorderC(borderSize: Dp, color: Color): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidthPx = borderSize.toPx()
        val halfStrokeWidthPx = strokeWidthPx / 2

        // Draw the top border
        drawLine(
            color = color,
            start = Offset(0f, halfStrokeWidthPx),
            end = Offset(size.width, halfStrokeWidthPx),
            strokeWidth = strokeWidthPx
        )
    }
)