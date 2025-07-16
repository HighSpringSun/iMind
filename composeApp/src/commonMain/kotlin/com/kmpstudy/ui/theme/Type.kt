package com.mywf.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.SystemFontFamily
import org.jetbrains.compose.resources.Font

//@Composable
//fun AppTypography(): Typography {
//    val displayFontFamily = SystemFontFamily()
//    val mapleFontFamily = FontFamily(
//        Font(
//            resource = Res.font.SourceHanSansCN_Regular,
//            weight = FontWeight.Thin,
//            style = FontStyle.Normal
//        ),
//        Font(
//            resource = Res.font.MapleMonoNormal_NF_CN_Light,
//            weight = FontWeight.Light,
//            style = FontStyle.Normal
//        ),
//        Font(
//            resource = Res.font.MapleMonoNormal_NF_CN_ExtraLight,
//            weight = FontWeight.ExtraLight,
//            style = FontStyle.Normal
//        ),
//        Font(
//            resource = Res.font.MapleMonoNormal_NF_CN_Regular,
//            weight = FontWeight.Normal,
//            style = FontStyle.Normal
//        ),
//        Font(
//            resource = Res.font.MapleMonoNormal_NF_CN_Medium,
//            weight = FontWeight.Normal,
//            style = FontStyle.Normal
//        ),
//        Font(
//            resource = Res.font.MapleMonoNormal_NF_CN_Bold,
//            weight = FontWeight.Bold,
//            style = FontStyle.Normal
//        ),
//        Font(
//            resource = Res.font.MapleMonoNormal_NF_CN_SemiBold,
//            weight = FontWeight.SemiBold,
//            style = FontStyle.Normal
//        ),
//        Font(
//            resource = Res.font.MapleMonoNormal_NF_CN_ExtraBold,
//            weight = FontWeight.ExtraBold,
//            style = FontStyle.Normal
//        )
//    )
//    val baseline = Typography()
//    val appTypography = Typography(
//        displayLarge = baseline.displayLarge.copy(fontFamily = mapleFontFamily),
//        displayMedium = baseline.displayMedium.copy(fontFamily = mapleFontFamily),
//        displaySmall = baseline.displaySmall.copy(fontFamily = mapleFontFamily),
//        headlineLarge = baseline.headlineLarge.copy(fontFamily = mapleFontFamily),
//        headlineMedium = baseline.headlineMedium.copy(fontFamily = mapleFontFamily),
//        headlineSmall = baseline.headlineSmall.copy(fontFamily = mapleFontFamily),
//        titleLarge = baseline.titleLarge.copy(fontFamily = mapleFontFamily),
//        titleMedium = baseline.titleMedium.copy(fontFamily = mapleFontFamily),
//        titleSmall = baseline.titleSmall.copy(fontFamily = mapleFontFamily),
//        bodyLarge = baseline.bodyLarge.copy(fontFamily = mapleFontFamily),
//        bodyMedium = baseline.bodyMedium.copy(fontFamily = mapleFontFamily),
//        bodySmall = baseline.bodySmall.copy(fontFamily = mapleFontFamily),
//        labelLarge = baseline.labelLarge.copy(fontFamily = mapleFontFamily),
//        labelMedium = baseline.labelMedium.copy(fontFamily = mapleFontFamily),
//        labelSmall = baseline.labelSmall.copy(fontFamily = mapleFontFamily),
//    )
//    val appTypography = Typography(
//        displayLarge = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.ExtraBold,
//            fontSize = 57.sp,
//            lineHeight = 64.sp,
//            letterSpacing = (-0.25).sp
//        ),
//        displayMedium = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 45.sp,
//            lineHeight = 52.sp
//        ),
//        displaySmall = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 36.sp,
//            lineHeight = 44.sp
//        ),
//
//        headlineLarge = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 32.sp,
//            lineHeight = 40.sp
//        ),
//        headlineMedium = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 28.sp,
//            lineHeight = 36.sp
//        ),
//        headlineSmall = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 24.sp,
//            lineHeight = 32.sp
//        ),
//
//        titleLarge = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 22.sp,
//            lineHeight = 28.sp
//        ),
//        titleMedium = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 16.sp,
//            lineHeight = 24.sp
//        ),
//        titleSmall = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 14.sp,
//            lineHeight = 20.sp
//        ),
//
//        bodyLarge = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Normal,
//            fontSize = 16.sp,
//            lineHeight = 24.sp
//        ),
//        bodyMedium = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Normal,
//            fontSize = 14.sp,
//            lineHeight = 20.sp
//        ),
//        bodySmall = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Normal,
//            fontSize = 12.sp,
//            lineHeight = 16.sp
//        ),
//
//        labelLarge = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 14.sp,
//            lineHeight = 20.sp
//        ),
//        labelMedium = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 12.sp,
//            lineHeight = 16.sp
//        ),
//        labelSmall = TextStyle(
//            fontFamily = mapleFontFamily,
//            fontWeight = FontWeight.Bold,
//            fontSize = 11.sp,
//            lineHeight = 16.sp
//        )
//    )
//    return remember { appTypography }
//}
