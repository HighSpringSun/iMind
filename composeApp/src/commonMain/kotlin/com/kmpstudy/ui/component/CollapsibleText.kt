package com.kmpstudy.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmpstudy.ui.modifier.noRippleClick
import com.kmpstudy.extensions.format
import compose.icons.TablerIcons
import compose.icons.tablericons.Dots
import comkmpstudy.sqldelight.Record


enum class ExpandCollapseState {
    None,
    Collapse,
    Expand,
}

class CollapsibleTextState(
    initialValue: ExpandCollapseState = ExpandCollapseState.None,
    val maxHeight: Dp
) {
    var value by mutableStateOf(initialValue)
    val height by derivedStateOf {
        when (value) {
            ExpandCollapseState.None -> maxHeight
            ExpandCollapseState.Collapse -> maxHeight
            ExpandCollapseState.Expand -> Dp.Unspecified
        }
    }

    fun switch() {
        switchValue()
    }

    fun changeToCollapse() {
        if (value == ExpandCollapseState.None) {
            value = ExpandCollapseState.Collapse
        }
    }

    fun checkIsNone(currentHeight: Dp): Boolean {
//        Napier.e { "currentHeight:$currentHeight" }
//        Napier.e { "maxHeight:$maxHeight" }
        return currentHeight <= maxHeight
    }

    private fun switchValue() {
        value = when (value) {
            ExpandCollapseState.None -> throw Exception("unknown why None")
            ExpandCollapseState.Collapse -> ExpandCollapseState.Expand
            ExpandCollapseState.Expand -> ExpandCollapseState.Collapse
        }
    }
}

@Composable
fun rememberCollapsibleTextState(
    initialValue: ExpandCollapseState = ExpandCollapseState.None,
    maxHeight: Dp
): CollapsibleTextState {
    return remember {
        CollapsibleTextState(
            initialValue = initialValue,
            maxHeight = maxHeight,
        )
    }
}


@Composable
fun CollapsibleText(
    record: Record,
) {
    val textState = rememberCollapsibleTextState(maxHeight = (160 + 2).dp)
    val str by remember {
        derivedStateOf {
            when (textState.value) {
                ExpandCollapseState.None -> "None"
                ExpandCollapseState.Collapse -> "展开"
                ExpandCollapseState.Expand -> "收起"
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            var expanded by remember { mutableStateOf(false) }

            Text(
                text = record.editAt.format(),
//                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                letterSpacing = (-0.3).sp,
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall
            )
            Box {
                Icon(
                    TablerIcons.Dots,
                    contentDescription = "More",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clickable {
                            expanded = true
                        }
                )

                // 下拉菜单
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset.Zero,
                    scrollState = rememberScrollState(),
                    shadowElevation = 3.dp
                ) {
                    DropdownMenuItem(
                        text = { Text("查看详情") },
                        onClick = {
                            // 处理点击
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("分享") },
                        onClick = {
                            // 处理点击
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("删除") },
                        onClick = {
                            // 处理点击
                            expanded = false
                        }
                    )
                }
            }
        }
        Text(
            text = record.content,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            overflow = TextOverflow.Visible,
            onTextLayout = { textLayoutResult ->
                if (textState.value == ExpandCollapseState.None &&
                    !textState.checkIsNone(16.times(textLayoutResult.lineCount).dp)
                ) {
//                    Napier.e { textLayoutResult.lineCount.toString() }
                    textState.changeToCollapse()
                }
            },
            modifier = Modifier
                .animateContentSize()
                .sizeIn(maxHeight = textState.height)
        )
        if (textState.value != ExpandCollapseState.None) {
            Text(
                text = str,
                color = Color(79, 132, 244),
                modifier = Modifier
                    .noRippleClick {
                        textState.switch()
                    }
            )
        }
    }
}