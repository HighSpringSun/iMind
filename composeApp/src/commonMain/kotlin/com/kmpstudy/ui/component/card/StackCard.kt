package com.kmpstudy.ui.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun StackCard(
    number: Int = 0,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    BaseCard(
        modifier = modifier
            .drawBehind {
                for (index in number - 1 downTo 0) {
                    drawRect(
                        color = backgroundColor,
                        topLeft = Offset((index + 1) * 15f, (index + 1) * 15f),
                        size = this.size,
                        style = Fill
                    )
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset((index + 1) * 15f, (index + 1) * 15f),
                        size = this.size,
                        style = Stroke(2f)
                    )
                }
            },
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        border = border,
        elevation = elevation
    ) {
        content()
    }
}