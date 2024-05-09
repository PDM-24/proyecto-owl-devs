package com.owldevs.taskme.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.owldevs.taskme.R

private val Lato = FontFamily(
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_italic, FontWeight.Normal),
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato_light_italic, FontWeight.Light),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_bold_italic, FontWeight.Bold),
)

private val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_italic, FontWeight.Normal),
    Font(R.font.nunito_medium, FontWeight.Medium),
    Font(R.font.nunito_medium_italic, FontWeight.Medium),
    Font(R.font.nunito_light, FontWeight.Light),
    Font(R.font.nunito_light_italic, FontWeight.Light),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_bold_italic, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(

    //Titulos
    titleLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),

    //Texto "normal"
    bodyLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )

    /*bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )*/
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