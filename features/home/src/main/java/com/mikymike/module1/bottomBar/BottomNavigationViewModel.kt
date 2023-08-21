package com.mikymike.module1.bottomBar

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikymike.home.R
import com.mikymike.module2.ui.GamesNavGraph
import com.mikymike.module2.ui.dot.GamesDotManager
import com.mikymike.module3.ShopNavGraph
import com.mikymike.module3.dot.ShopDotManager
import com.mikymike.profile.ui.ProfileNavGraph
import com.mikymike.profile.ui.dot.ProfileDotManager
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

data class BottomBarUi(
    val items: List<BottomBarItemUi> = emptyList()
)

data class BottomBarItemUi(
    val showDot: Boolean = false,
    val screen: NavGraphSpec,
    val label: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val iconSelectedRes: Int
)

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    gamesDotManager: GamesDotManager,
    shopDotManager: ShopDotManager,
    profileDotManager: ProfileDotManager
) : ViewModel() {

    private val _uiState: MutableStateFlow<BottomBarUi> = MutableStateFlow(createInitialState())
    val uiState: StateFlow<BottomBarUi> = _uiState

    init {
        combine(
            gamesDotManager.showGamesDot,
            shopDotManager.showShopDot,
            profileDotManager.showProfileDot
        ) { showGamesDot, showShopDot, showProfileDot ->
            val gamesItem = _uiState.value.items[0].copy(showDot = showGamesDot)
            val shopItem = _uiState.value.items[1].copy(showDot = showShopDot)
            val profileItem = _uiState.value.items[2].copy(showDot = showProfileDot)

            val items = listOf(gamesItem, shopItem, profileItem)

            _uiState.emit(BottomBarUi(items = items))
        }.launchIn(viewModelScope)
    }

    private fun createInitialState() = BottomBarUi(
        items = listOf(
            BottomBarItemUi(
                screen = GamesNavGraph,
                iconRes = R.drawable.ic_games,
                iconSelectedRes = R.drawable.ic_games_selected,
                label = "Games"
            ), BottomBarItemUi(
                screen = ShopNavGraph,
                iconRes = R.drawable.ic_shop,
                iconSelectedRes = R.drawable.ic_shop_selected,
                label = "Shop"
            ), BottomBarItemUi(
                screen = ProfileNavGraph,
                iconRes = R.drawable.ic_profile,
                iconSelectedRes = R.drawable.ic_profile_selected,
                label = "Profile"
            )
        )
    )
}