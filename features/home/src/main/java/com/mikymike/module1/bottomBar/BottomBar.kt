package com.mikymike.module1.bottomBar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.spec.NavGraphSpec

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    viewModel: BottomNavigationViewModel = hiltViewModel(),
    selectedNavigation: NavGraphSpec,
    onNavigationSelected: (NavGraphSpec) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Gray,
        contentColor = Color.White,
    ) {
        state.items.forEach {
            val selected = selectedNavigation == it.screen

            BottomNavigationItem(
                modifier = Modifier.fillMaxHeight(),
                icon = {
                    BottomBarItem(
                        showDot = it.showDot,
                        isSelected = selected,
                        iconRes = it.iconRes,
                        iconSelectedRes = it.iconSelectedRes,
                    )
                },
                label = { Text(text = it.label) },
                selected = selected,
                onClick = { onNavigationSelected(it.screen) },
                unselectedContentColor = Color.White,
                selectedContentColor = Color(0xFF6B28E6)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    modifier: Modifier = Modifier,
    showDot: Boolean = false,
    isSelected: Boolean = false,
    @DrawableRes iconRes: Int,
    @DrawableRes iconSelectedRes: Int,
) {
    BadgedBox(modifier = modifier, badge = { if (showDot) Badge(backgroundColor = Color.Cyan) }) {
        Icon(
            modifier = Modifier.height(26.dp),
            painter = painterResource(id = if (isSelected) iconSelectedRes else iconRes),
            contentDescription = null
        )
    }
}