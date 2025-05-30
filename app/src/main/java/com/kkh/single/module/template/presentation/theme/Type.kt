package com.kkh.single.module.template.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kkh.single.module.template.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

object NeodinaryTypography {
    val Splash_SemiBold = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
        fontSize = 26.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.SemiBold
    )
    val Headline1_Bold = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
        fontSize = 22.sp,
        lineHeight = 30.8.sp ,
        fontWeight = FontWeight.Bold
    )
    val HeadLine2_SemiBold = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
        fontSize = 20.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.SemiBold
    )
    val HeadLine2_Medium = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 20.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Medium
    )
    val Subtitle1_SemiBold = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
        fontSize = 18.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.SemiBold
    )
    val Subtitle1_Regular = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 18.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Normal
    )
    val Subtitle1_Medium = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 18.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Medium
    )
    val Body1_Medium = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 16.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Medium
    )
    val Body1_Regular = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 16.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Normal
    )
    val Body2_Medium = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 14.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Medium
    )
    val OnBoarding_Normal = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 15.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Medium
    )
    val Body2_Regular = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 14.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Normal
    )
    val Caption_Medium = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 12.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Medium
    )
    val Caption_Regular = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 12.sp,
        lineHeight = 30.8.sp,
        fontWeight = FontWeight.Normal
    )

}
