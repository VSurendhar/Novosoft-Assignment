package com.noasoftsolutions.machineCoding.screens

import com.noasoftsolutions.machineCoding.R

sealed class Screens(
    val route: String,
    val label: String,
    val outlineIcon: Int,
    val filledIcon: Int
) {
    object HomeScreen :
        Screens("home_screen", "Home", R.drawable.house_outline, R.drawable.house_filled)

    object BankScreen :
        Screens("bank_screen", "Bank", R.drawable.bank_outline, R.drawable.bank_filled)

    object HistoryScreen :
        Screens("history_screen", "History", R.drawable.history_outline, R.drawable.history_outline)

    object ProfileScreen :
        Screens("profile_screen", "Profile", R.drawable.profile_outline, R.drawable.profile_filled)
}
