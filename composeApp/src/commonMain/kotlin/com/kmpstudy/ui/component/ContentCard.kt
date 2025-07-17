package com.kmpstudy.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableDefaults
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kmpstudy.data.db.db
import comkmpstudy.sqldelight.Record
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Close
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


enum class DragAnchors {
    Start,
    End
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentCard(records: Record) {
    var isDel by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = !isDel,
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            val density = LocalDensity.current
            val defaultActionSize = remember { 80.dp }
            val endActionSizePx = with(density) { (defaultActionSize).toPx() }
            val state = remember {
                AnchoredDraggableState(
                    initialValue = DragAnchors.Start,
                    anchors = DraggableAnchors {
                        DragAnchors.Start at 0f
                        DragAnchors.End at endActionSizePx
                    }
                )
            }

            Card(
                modifier = Modifier
                    .anchoredDraggable(
                        state = state,
                        orientation = Orientation.Horizontal,
                        reverseDirection = true,
                        flingBehavior = AnchoredDraggableDefaults.flingBehavior(
                            state = state,
                        )
                    )
                    .offset {
                        IntOffset(
                            x = -state.requireOffset().roundToInt(),
                            y = 0
                        )
                    },
                shape = RoundedCornerShape(8.dp),
                border = CardDefaults.outlinedCardBorder(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CollapsibleText(records)
                    }
                }
            }
            AnimatedVisibility(
                visible = state.requireOffset() > 0f,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(with(density) { state.requireOffset().toDp() })
                    .fillMaxHeight(),
                enter = slideInHorizontally(),
                exit = slideOutHorizontally()
            ) {
                val coroutineScope = rememberCoroutineScope()
                Icon(
                    imageVector = EvaIcons.Fill.Close,
                    contentDescription = "Remove",
                    tint = Color.Red,
                    modifier = Modifier
                        .width(with(density) { state.requireOffset().toDp() / 2 })
                        .clickable {
                            coroutineScope.launch {
                                isDel = true
                                delay(300)
                                db.databaseQueries.deleteRecord(records.id)
//                                Napier.e { "id:${records.id}" }
                            }
                        },
                )
            }
        }
    }
}