package com.kmpstudy.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kmpstudy.ui.modifier.noRippleClick
import com.kmpstudy.viewmodel.PlusButtonViewModel
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.PlusSquare
import kotlinx.coroutines.launch
import org.kodein.di.compose.viewmodel.rememberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlusButton(modalBottomSheetState: SheetState) {
    val coroutineScope = rememberCoroutineScope()
    val plusState by rememberViewModel<PlusButtonViewModel>()
    AnimatedVisibility(
        visible = plusState.showPlus,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        // 固定子项的位置 居中
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            // plusSquare中间的加号是空的，需要这个box挡住
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(Color.White)
            )
            Icon(
                imageVector = EvaIcons.Fill.PlusSquare,
                contentDescription = "Add",
                modifier = Modifier
                    .size(68.dp)
                    .padding(6.dp)
                    .noRippleClick {
                        plusState.hide()
                        coroutineScope.launch {
                            modalBottomSheetState.show()
                        }
                    },
                tint = Color(0, 211, 112)
            )
        }
    }
}