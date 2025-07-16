package com.kmpstudy.ui.modifier

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role


fun Modifier.noRippleClick(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed {
    Modifier
        .clickable(
            interactionSource = null,
            indication = null,
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role
        ) {
            onClick()
        }
}