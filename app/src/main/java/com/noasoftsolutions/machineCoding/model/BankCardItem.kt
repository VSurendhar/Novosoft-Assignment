package com.noasoftsolutions.machineCoding.model

interface BankCardItem {
    val accountBalance: String
    val leftButton: () -> Unit
    val rightButton: () -> Unit
}

data class BankCard(
    val accountNumber: String,
    val ifsc: String,
    val bankName: String,
    override val accountBalance: String,
    override val leftButton: () -> Unit,
    override val rightButton: () -> Unit,
    val img: Int
) : BankCardItem

data class Wallet(
    override val accountBalance: String,
    override val leftButton: () -> Unit,
    override val rightButton: () -> Unit
) : BankCardItem

