package com.kmpstudy.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmpstudy.viewmodel.RecordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.compose.viewmodel.rememberViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRefreshLazyColumn() {
    val recordViewModel by rememberViewModel<RecordViewModel>()
    val records by recordViewModel.records.collectAsState(listOf())
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()


    // 当数据更新时（这里简单假设通过其他方式改变了records列表），手动将滚动位置设置到顶部
    LaunchedEffect(records.size) {
        if (records.isNotEmpty()) {
            coroutineScope.launch {
                scrollState.animateScrollToItem(0)
            }
        }
    }

    var isRefreshing by remember { mutableStateOf(false) }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        modifier = Modifier
            .fillMaxSize(),
        onRefresh = {
            isRefreshing = true
            coroutineScope.launch {
                recordViewModel.refresh()
                delay(300)
                isRefreshing = false
            }
        }
    ) {
        AnimatedVisibility(
            visible = !isRefreshing,
            enter = fadeIn() + scaleIn(),
            exit = ExitTransition.None
        ) {
            LazyColumn(
                state = scrollState,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = records.size,
                    key = { records[it].id }
                ) {
                    ContentCard(records[it])
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = "到底了..",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}