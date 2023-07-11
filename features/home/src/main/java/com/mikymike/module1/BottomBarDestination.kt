package com.mikymike.module1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.mikymike.module2.GamesNavGraph
import com.mikymike.module3.ShopNavGraph
import com.mikymike.profile.ProfileNavGraph
import com.ramcosta.composedestinations.spec.NavGraphSpec

enum class BottomBarDestination(
    val screen: NavGraphSpec, val icon: ImageVector, val label: String
) {
    Games(screen = GamesNavGraph, icon = Icons.Default.Home, "Games"), Shop(
        screen = ShopNavGraph, icon = Icons.Default.ShoppingCart, "Shop"
    ),
    Profile(screen = ProfileNavGraph, icon = Icons.Default.Person, "Profile")
}