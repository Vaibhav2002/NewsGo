package dev.vaibhav.newsapp.android.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.vaibhav.newsapp.android.R

//Replace with your font locations
val montserrat = FontFamily(
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_regular, FontWeight.W400)
)

val openSans = FontFamily(
    Font(R.font.open_sans_medium, FontWeight.Medium),
    Font(R.font.open_sans_regular, FontWeight.W400)
)


val AppTypography = Typography(
  labelLarge = TextStyle(
      fontFamily = openSans,
      fontWeight = FontWeight.Medium,
      letterSpacing = 0.10000000149011612.sp,
      lineHeight = 20.sp,
      fontSize = 14.sp
  ),
  labelMedium = TextStyle(
      fontFamily = openSans,
      fontWeight = FontWeight.Medium,
      letterSpacing = 0.5.sp,
      lineHeight = 16.sp,
      fontSize = 12.sp
  ),
  labelSmall = TextStyle(
      fontFamily = openSans,
      fontWeight = FontWeight.Medium,
      letterSpacing = 0.5.sp,
      lineHeight = 16.sp,
      fontSize = 11.sp
  ),
  bodyLarge = TextStyle(
      fontFamily = openSans,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.5.sp,
      lineHeight = 24.sp,
      fontSize = 16.sp
  ),
  bodyMedium = TextStyle(
      fontFamily = openSans,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.25.sp,
      lineHeight = 20.sp,
      fontSize = 14.sp
  ),
  bodySmall = TextStyle(
      fontFamily = openSans,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.4000000059604645.sp,
      lineHeight = 16.sp,
      fontSize = 12.sp
  ),
  headlineLarge = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.sp,
      lineHeight = 40.sp,
      fontSize = 32.sp
  ),
  headlineMedium = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.sp,
      lineHeight = 36.sp,
      fontSize = 28.sp
  ),
  headlineSmall = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.sp,
      lineHeight = 32.sp,
      fontSize = 24.sp
  ),
  displayLarge = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.W400,
      letterSpacing = (-0.25).sp,
      lineHeight = 64.sp,
      fontSize = 57.sp
  ),
  displayMedium = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.sp,
      lineHeight = 52.sp,
      fontSize = 45.sp
  ),
  displaySmall = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.sp,
      lineHeight = 44.sp,
      fontSize = 36.sp
  ),
  titleLarge = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.sp,
      lineHeight = 28.sp,
      fontSize = 22.sp
  ),
  titleMedium = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.Medium,
      letterSpacing = 0.15000000596046448.sp,
      lineHeight = 24.sp,
      fontSize = 16.sp
  ),
  titleSmall = TextStyle(
      fontFamily = montserrat,
      fontWeight = FontWeight.Medium,
      letterSpacing = 0.10000000149011612.sp,
      lineHeight = 20.sp,
      fontSize = 14.sp
  ),
)