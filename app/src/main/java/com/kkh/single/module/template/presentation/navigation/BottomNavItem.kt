package com.kkh.single.module.template.presentation.navigation

import com.kkh.single.module.template.R

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object Calendar :
        BottomNavItem("title_calendar", R.drawable.ic_home_category, HomeRoutes.MAIN)

    object Timeline :
        BottomNavItem("title_calendar", R.drawable.ic_home_home, HomeRoutes.SELECT_DATE)

    object Analysis :
        BottomNavItem("title_calendar", R.drawable.ic_home_face, HomeRoutes.MYPAGE)

}