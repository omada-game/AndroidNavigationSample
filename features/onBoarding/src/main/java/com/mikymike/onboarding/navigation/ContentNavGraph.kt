package com.mikymike.onboarding.navigation

import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph(default = false)
annotation class ContentNavGraph(
    val start: Boolean = false
)
