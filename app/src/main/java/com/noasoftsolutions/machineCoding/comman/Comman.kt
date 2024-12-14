package com.noasoftsolutions.machineCoding.comman

import com.noasoftsolutions.machineCoding.R
import com.noasoftsolutions.machineCoding.model.BankCard
import com.noasoftsolutions.machineCoding.model.BankTitleCardDetail
import com.noasoftsolutions.machineCoding.model.CardItem
import com.noasoftsolutions.machineCoding.model.Options
import com.noasoftsolutions.machineCoding.model.ReceiveMoneyOptions
import com.noasoftsolutions.machineCoding.model.Transaction
import com.noasoftsolutions.machineCoding.model.Wallet

object Comman {

    const val TAG = "NoaSoftSolutions"

    val bankCards = listOf(
        BankTitleCardDetail("Qpay wallet", "₹2,36,000.47", R.drawable.wallet),
        BankTitleCardDetail("SBI", "₹2,36,000.47", R.drawable.sbi),
        BankTitleCardDetail("Bank of Baroda", "₹2,36,000.47", R.drawable.bob)
    )

    val sbiDetails = BankCard(
        accountNumber = "XXXXXXXXXXXX4536",
        ifsc = "KKBK0008798",
        bankName = "State Bank of India",
        accountBalance = "₹2,36,000.47",
        leftButton = {},
        rightButton = {},
        img = R.drawable.sbi
    )

    val receiveMoneyOptions = listOf(
        ReceiveMoneyOptions("QR Code", R.drawable.qr),
        ReceiveMoneyOptions("Copy", R.drawable.copy),
        ReceiveMoneyOptions("Share", R.drawable.share)
    )

    val bobDetails = BankCard(
        accountNumber = "XXXXXXXXXXXX4536",
        ifsc = "KKBK0008798",
        bankName = "Bank of Baroda",
        accountBalance = "₹2,36,000.47",
        leftButton = {},
        rightButton = {},
        img = R.drawable.bob
    )

    val wallet = Wallet(
        accountBalance = "₹2,36,000.47",
        leftButton = { /* Add money logic here */ },
        rightButton = { /* Transfer money logic here */ }
    )

    val cardItems = listOf(
        CardItem("Mobile Recharge", R.drawable.mobile),
        CardItem("Electricity Payments", R.drawable.electric),
        CardItem("Credit Card Bill", R.drawable.card),
        CardItem("DTH / Cable TV", R.drawable.cabletv),
        CardItem("Gas Cylinder", R.drawable.gas),
        CardItem("More Payments", R.drawable.category)
    )

    val transactions = listOf(
        Transaction(R.drawable.account_outline, "John"),
        Transaction(R.drawable.account_outline, "John"),
        Transaction(R.drawable.account_outline, "John"),
        Transaction(R.drawable.account_outline, "John"),
        Transaction(R.drawable.account_outline, "John"),
        Transaction(R.drawable.account_outline, "John"),
    )

    val bankAccounts = listOf(sbiDetails, bobDetails)


    val securityOptions = listOf(
        Options(R.drawable.finger_print, "Screen Lock", "Biometrics & Screen Locks"),
        Options(R.drawable.change_password, "Change Password", "Reset your app password")
    )

    val settingsPreferences =
        listOf(
            Options(
                primaryText = "Language",
                secondaryText = "Choose languages: English",
                icon = R.drawable.languages
            ),
            Options(
                primaryText = "Permissions",
                secondaryText = "Manage data sharing settings",
                icon = R.drawable.document
            )
        )

    val helpSupport = listOf(
        Options(
            primaryText = "Help",
            secondaryText = "BioMetric & Screen Lock",
            icon = R.drawable.help
        ),
        Options(
            primaryText = "Report fraud",
            secondaryText = "Reset your app password",
            icon = R.drawable.report
        ),
        Options(
            primaryText = "Privacy Policy",
            secondaryText = "Reset your app password",
            icon = R.drawable.privacy_policy
        ),
        Options(primaryText = "About us", secondaryText = "About Us", icon = R.drawable.about_us)
    )


}