package com.mikymike.navigationomada.ui.navgraphs

import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph(default = true)
annotation class MainNavGraph(
    val start: Boolean = false
)
