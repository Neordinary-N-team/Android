package com.kkh.single.module.template.presentation.navigation

import com.kkh.single.module.template.R

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object MENU :
        BottomNavItem("title_menu", R.drawable.ic_home_category, HomeRoutes.MENU)

    object MAIN :
        BottomNavItem("title_home", R.drawable.ic_home_home, HomeRoutes.MAIN)

    object MYPAGE :
        BottomNavItem("title_mypage", R.drawable.ic_home_face, HomeRoutes.MYPAGE)

}