package com.zzz.dishesapp.core.presentation.nav

/**
 * NOTE - the app doesn't have any navigation logic, this is just for setup
 * @author zyzz
 */
sealed class Screen {
    data object Home : Screen()
    data object Reheat : Screen()
    data object Preset : Screen()
    data object Copilot : Screen()
    data object Flavor : Screen()
    data object CareMode : Screen()
    data object Support : Screen()
}