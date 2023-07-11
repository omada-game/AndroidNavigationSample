package com.mikymike.module1

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.spec.NavGraphSpec

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedNavigation: NavGraphSpec,
    onNavigationSelected: (NavGraphSpec) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Gray,
        contentColor = Color.White,
    ) {
        BottomBarDestination.values().forEach { item ->
            BottomNavigationItem(icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = selectedNavigation == item.screen,
                onClick = { onNavigationSelected(item.screen) })
        }
    }
}