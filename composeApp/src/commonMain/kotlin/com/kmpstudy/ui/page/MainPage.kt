package com.kmpstudy.ui.page

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.kmpstudy.LocalDrawerState
import com.kmpstudy.ui.component.MessageContainer
import com.kmpstudy.ui.component.PlusButton
import com.kmpstudy.ui.component.PullRefreshLazyColumn
import com.kmpstudy.ui.component.SearchBar


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val drawerState = LocalDrawerState.current
    val snackBarHostState = remember { SnackbarHostState() }
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = {
            if (it == SheetValue.Hidden) {
                keyboardController?.hide()
            }
            true
        }
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape
            ) {
                DrawerContent()
            }
        },
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                SearchBar(drawerState, sharedTransitionScope, animatedContentScope)
            },
            snackbarHost = { SnackbarHost(snackBarHostState) },
            floatingActionButton = {
                PlusButton(modalBottomSheetState)
            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
            ) {
                BottomSheet(modalBottomSheetState) { modalBottomSheetState ->
//                    Content(drawerState, snackBarHostState, modalBottomSheetState)
                    PullRefreshLazyColumn()
                }
            }
        }
    }
    MessageContainer()
}