package com.kmpstudy.ui.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.kmpstudy.data.db.db
import com.kmpstudy.extensions.emptyStr
import com.kmpstudy.model.Message
import com.kmpstudy.model.MessageType
import com.kmpstudy.viewmodel.MessageViewModel
import com.kmpstudy.viewmodel.PlusButtonViewModel
import comkmpstudy.sqldelight.Record
import compose.icons.TablerIcons
import compose.icons.tablericons.Send
import kotlinx.coroutines.launch
import org.kodein.di.compose.viewmodel.rememberViewModel
import kotlin.time.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    modalBottomSheetState: SheetState,
    content: @Composable (modalBottomSheetState: SheetState) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val plusState by rememberViewModel<PlusButtonViewModel>()

    LaunchedEffect(modalBottomSheetState.isVisible) {
        if (modalBottomSheetState.isVisible) {
            keyboardController?.show()
            focusRequester.requestFocus()
        } else {
            if (!plusState.showPlus) {
                plusState.show()
            }
        }
    }

    content(modalBottomSheetState)

    AnimatedVisibility(
        modalBottomSheetState.isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ModalBottomSheet(
            sheetState = modalBottomSheetState,
            onDismissRequest = {
                plusState.show()
            },
            shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
            dragHandle = null, // 可以隐藏默认的拖动手柄
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val msgViewModel by rememberViewModel<MessageViewModel>()
                var value by remember { mutableStateOf("") }

                val largeRadialGradient = remember {
                    object : ShaderBrush() {
                        override fun createShader(size: Size): Shader {
                            val biggerDimension = maxOf(size.height, size.width)
                            return RadialGradientShader(
                                colors = listOf(Color.Gray, Color.White),
                                center = size.center,
                                radius = biggerDimension / 2f,
                                colorStops = listOf(0f, 0.95f)
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    modifier = Modifier
                        .heightIn(min = 100.dp, max = 300.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    placeholder = {
                        Text("现在的想法是...")
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .background(largeRadialGradient)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = {
                            if (value.isEmpty()) {
                                msgViewModel.showMessage(
                                    Message(
                                        type = MessageType.Warning,
                                        content = "输入别为空的"
                                    )
                                )
                                return@OutlinedButton
                            }
                            keyboardController?.hide()
                            coroutineScope.launch {
                                val createAt = Clock.System.now()
                                db.databaseQueries.insertRecord(
                                    Record(0, value, createAt, createAt)
                                )
                                modalBottomSheetState.hide()
                                value = emptyStr
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0, 211, 112, 30),
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = TablerIcons.Send,
                            contentDescription = "Save",
                            tint = Color(0, 211, 112)
                        )
                    }
                }
            }
        }
    }
}