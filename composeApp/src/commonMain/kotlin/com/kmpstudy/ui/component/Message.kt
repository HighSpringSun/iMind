package com.kmpstudy.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kmpstudy.model.Message
import com.kmpstudy.model.MessageType
import com.kmpstudy.viewmodel.MessageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.compose.viewmodel.rememberViewModel


@Composable
fun MessageContainer() {
    val viewModel by rememberViewModel<MessageViewModel>()
    val coroutineScope = rememberCoroutineScope()

    // 监听消息列表变化，有新消息时显示消息组件
    LaunchedEffect(viewModel.messages) {
        if (viewModel.messages.isNotEmpty()) {
            viewModel.isShowingMessage.value = true
        }
    }

    Column(
        modifier = Modifier
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 遍历消息列表，显示每个消息并处理自动消失逻辑
        viewModel.messages.forEach { message ->
            val dismissMessage = { viewModel.removeMessage(message) }
            var showMsg by remember { mutableStateOf(false) }
            AnimatedVisibility(
                showMsg,
                enter = expandVertically(),
                exit = slideOutVertically()
            ) {
                AutoDismissingMessage(message)
//                Message(message, onDismiss = dismissMessage)
            }

            // 使用协程实现消息自动消失，这里假设3秒后自动关闭消息，可根据需求调整时间
            LaunchedEffect(message) {
                coroutineScope.launch {
                    showMsg = true
                    delay(3000L)
                    showMsg = false
                    dismissMessage()
                }
            }
        }
    }
}


@Composable
fun AutoDismissingMessage(
    message: Message,
    modifier: Modifier = Modifier
) {

    val backgroundColor = when (message.type) {
        is MessageType.Info -> MaterialTheme.colorScheme.secondary
        is MessageType.Success -> MaterialTheme.colorScheme.tertiary
        is MessageType.Warning -> MaterialTheme.colorScheme.background
        is MessageType.Error -> MaterialTheme.colorScheme.errorContainer
    }

    val contentColor = when (message.type) {
        is MessageType.Info -> MaterialTheme.colorScheme.onSecondary
        is MessageType.Success -> MaterialTheme.colorScheme.onTertiary
        is MessageType.Warning -> MaterialTheme.colorScheme.onBackground
        is MessageType.Error -> MaterialTheme.colorScheme.onErrorContainer
    }

    val icon = when (message.type) {
        is MessageType.Info -> Icons.Filled.Info
        is MessageType.Success -> Icons.Filled.CheckCircle
        is MessageType.Warning -> Icons.Filled.Warning
        is MessageType.Error -> Icons.Filled.Close
    }


    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


//@Composable
//fun Message(
//    message: Message,
//    onDismiss: () -> Unit
//) {
//    val backgroundColor = remember {
//        when (message.type) {
//            is MessageType.Info -> Color.Blue.copy(alpha = 0.8f)
//            is MessageType.Success -> Color.Green.copy(alpha = 0.8f)
//            is MessageType.Warning -> Color.Yellow.copy(alpha = 0.8f)
//            is MessageType.Error -> Color.Red.copy(alpha = 0.8f)
//        }
//    }
//
//    val icon = remember {
//        when (message.type) {
//            is MessageType.Info -> Icons.Filled.Info
//            is MessageType.Success -> Icons.Filled.CheckCircle
//            is MessageType.Warning -> Icons.Filled.Warning
//            is MessageType.Error -> Icons.Filled.Close
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .background(backgroundColor)
//            .height(48.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = null,
//                tint = Color.Black,
//                modifier = Modifier.size(24.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = message.content,
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Black,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.weight(1f)
//            )
//            IconButton(
//                onClick = onDismiss,
//                modifier = Modifier.size(24.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Close,
//                    contentDescription = "关闭消息",
//                    tint = Color.Black
//                )
//            }
//        }
//    }
//}
