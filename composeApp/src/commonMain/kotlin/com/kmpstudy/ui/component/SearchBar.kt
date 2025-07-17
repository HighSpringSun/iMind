package com.kmpstudy.ui.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmpstudy.LocalNavController
import com.kmpstudy.ui.modifier.noRippleClick
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.ArrowCircleDown
import compose.icons.evaicons.outline.Menu
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Logo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "iMIND",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
        )
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = it
            }
        ) {
            Icon(
                imageVector = EvaIcons.Fill.ArrowCircleDown,
                contentDescription = "ArrowCircleDown",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(16.dp)
                    .noRippleClick {
                        expanded = true
                    }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(80.dp)
            ) {
                Text("123")
                Text("123")
                Text("123")
                Text("123")
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchBar(
    drawerState: DrawerState,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    with(sharedTransitionScope) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp)
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val coroutineScope = rememberCoroutineScope()
                Icon(
                    imageVector = EvaIcons.Outline.Menu,
                    contentDescription = "Menu",
                    tint = Color.Gray,
                    modifier = Modifier
                        .noRippleClick {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                )
                Logo()
            }

            val localNavController = LocalNavController.current
            Icon(
                imageVector = Icons.Outlined.Search,//EvaIcons.Outline.Search
                contentDescription = "Search",
                tint = Color.Gray,
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "search"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .noRippleClick {
                        localNavController.navigate("SearchPage")
                    }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}


//        val (value, onChanged) = remember { mutableStateOf(emptyStr) }
//        var isFocused by remember { mutableStateOf(false) }
//        OutlinedTextField(
//            value = value,
//            onValueChange = onChanged,
//            trailingIcon = {
//                Icon(
//                    Icons.Outlined.Search,
//                    contentDescription = "Search"
//                )
//            },
//            singleLine = true,
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Cyan
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .onFocusChanged {
//                    isFocused = it.isFocused
//                }
//        )
//        AnimatedVisibility(
//            !isFocused
//        ) {
//            Text(
//                text = "iMIND",
//                fontSize = 16.sp,
//                fontStyle = FontStyle.Italic,
//                fontWeight = FontWeight.Bold,
//                fontFamily = FontFamily.Cursive
//            )
//        }
