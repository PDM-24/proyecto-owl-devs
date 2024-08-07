package com.owldevs.taskme.ui.navigation


//Resto de rutas que NO se encuentran en la barra inferior
sealed class SecondaryScreens(val route: String) {

    data object CreateOrHave : SecondaryScreens("create_or_have")

    data object CategoryScreen: SecondaryScreens("categoryScreen/{categoryName}")

    data object ChatScreen: SecondaryScreens("chatScreen")

    data object FAQScreen: SecondaryScreens("faqScreen")

    data object LoginScreen: SecondaryScreens("loginScreen")

    data object ReviewsScreen: SecondaryScreens("reviewsScreen")

    data object RegisterClientOrTask : SecondaryScreens("register_client_or_task")

    data object ScheduleDetail: SecondaryScreens("scheduleDetail")

    data object ScheduleScreen: SecondaryScreens("scheduleScreen")

    data object Support : SecondaryScreens("support")

    data object RegisterClient : SecondaryScreens("register_client")

    data object RegisterTasker : SecondaryScreens("register_tasker")

    data object UserOrder: SecondaryScreens("userOrder")

    data object UserSettings: SecondaryScreens("userSettingsScreen")

    data object UserMailbox: SecondaryScreens("userMailbox")

    data object TaskScreen: SecondaryScreens("taskScreen")

    data object EditProfile : SecondaryScreens ("editProfile")

    data object AddTaskToProfile : SecondaryScreens ("addTask")

    data object AddReview : SecondaryScreens("addReview")
    data object UserFunds: SecondaryScreens("userfundsScreen")

    data object PaymentCardScreen: SecondaryScreens("paymentScreen")

    data object AddCardScreen: SecondaryScreens("addCardScreen")

    data object TypeOfPaymentScreen: SecondaryScreens("typeOfPaymentScreen")

    data object SuccesfulPaymentScreen: SecondaryScreens("succefulPaymentScreen")
    data object UserTaskScreen : SecondaryScreens("UserTaskScreen")

    data object TaskerInfoScreen : SecondaryScreens("taskerInfo")

    data object UsertoTasker : SecondaryScreens("UsertoTasker")

    data object TermsConditions : SecondaryScreens("TermsConditions")

}