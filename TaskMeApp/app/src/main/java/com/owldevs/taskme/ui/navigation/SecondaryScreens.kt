package com.owldevs.taskme.ui.navigation


//Resto de rutas que NO se encuentran en la barra inferior
sealed class SecondaryScreens(val route: String) {

    data object CategoryScreen: SecondaryScreens("categoryScreen")

    data object ChatScreen: SecondaryScreens("chatScreen")

    data object FAQScreen: SecondaryScreens("faqScreen")

    data object LoginScreen: SecondaryScreens("loginScreen")

    data object ReviewsScreen: SecondaryScreens("reviewsScreen")

    data object ScheduleDetail: SecondaryScreens("scheduleDetail")

    data object ScheduleScreen: SecondaryScreens("scheduleScreen")

    data object UserOrder: SecondaryScreens("userOrder")

    data object UserSettings: SecondaryScreens("userSettingsScreen")

    data object UserMailbox: SecondaryScreens("userMailbox")

    data object TaskScreen: SecondaryScreens("taskScreen")
    data object EditProfile : Screens ("editProfile")

}