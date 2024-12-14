package com.noasoftsolutions.machineCoding.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.noasoftsolutions.machineCoding.R
import com.noasoftsolutions.machineCoding.comman.Comman.bankAccounts
import com.noasoftsolutions.machineCoding.comman.Comman.helpSupport
import com.noasoftsolutions.machineCoding.comman.Comman.securityOptions
import com.noasoftsolutions.machineCoding.comman.Comman.settingsPreferences
import com.noasoftsolutions.machineCoding.model.BankCard
import com.noasoftsolutions.machineCoding.model.Options
import com.noasoftsolutions.machineCoding.ui.theme.primaryColor

@Composable
fun ProfileScreen(modifier: Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        ProfileTitleBar()
        WalletBalance()
        ReceivingMoney()
        BankAccounts()
        SecurityProtection()
        SettingsPreferences()
        HelpSupport()
        Logout()
    }
}

@Composable
fun ReceivingMoney() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(100.dp)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color.Gray),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Receiving money in")
            Row(Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.sbi),
                    contentDescription = "sbi Icon"
                )
                Column {
                    Text(text = "xxxx 3941")
                    Text(text = "State Bank of India", style = TextStyle(color = Color.Gray))
                }
                OutlineButton(
                    onClick = {},
                    text = "Manage",
                    icon = painterResource(R.drawable.manage),
                    borderwidth = 1.dp,
                    borderColor = primaryColor,
                    textColor = primaryColor,
                    modifier = Modifier,
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )
            Row {
                Image(painter = painterResource(R.drawable.upi), contentDescription = "upi")
                Text(text = "1234567899@qpay")
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.copy),
                        contentDescription = "upi",
                        tint = primaryColor
                    )
                }
            }
        }
    }
}

@Composable
fun CommonCard(
    title: String,
    items: List<Options>,
    onItemClick: (Options) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Options) -> Unit
) {
    Card(
        modifier = modifier.padding(16.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = title)
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                Card(
                    colors = CardColors(
                        containerColor = Color.White,
                        contentColor = Color.Unspecified,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { onItemClick(item) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        content(item)
                    }
                }
            }
        }
    }
}

@Composable
fun Logout() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        FilledButton(
            onClick = {},
            text = "Logout",
            contentColor = Color.White,
            containerColor = Color.Red.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(1.dp, Color.Unspecified, shape = RoundedCornerShape(20.dp)),
            icon = painterResource(R.drawable.logout)
        )
    }
}

@Composable
fun SettingsPreferences() {
    CommonCard(
        title = "Settings & Preferences",
        items = settingsPreferences,
        onItemClick = {},
    ) { item ->
        Row {
            Image(
                painter = painterResource(item.icon),
                contentDescription = item.primaryText
            )
            Column {
                Text(text = item.primaryText)
                Text(text = item.secondaryText)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.next), contentDescription = "Next")
            }
        }
    }
}

@Composable
fun SecurityProtection() {
    CommonCard(
        title = "Security & Protection",
        items = securityOptions,
        onItemClick = {},
    ) { item ->
        Row {
            Image(
                painter = painterResource(item.icon),
                contentDescription = item.primaryText
            )
            Column {
                Text(text = item.primaryText)
                Text(text = item.secondaryText)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.next), contentDescription = "Next")
            }
        }
    }
}

@Composable
fun HelpSupport() {
    CommonCard(
        title = "Help & Support",
        items = helpSupport,
        onItemClick = {},
    ) { item ->
        Row {
            Image(painter = painterResource(item.icon), contentDescription = item.primaryText)
            Column {
                Text(text = item.primaryText)
                Text(text = item.secondaryText)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.next), contentDescription = "Next")
            }
        }
    }
}

@Composable
fun BankAccounts() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(300.dp)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color.Gray),
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Bank accounts")
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                bankAccounts.forEach { item ->
                    BankAccountItem(item)
                    if (item != bankAccounts.last()) {
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun BankAccountItem(bankCard: BankCard, isPrimary: Boolean = false) {
    Card(
        modifier = Modifier.padding(15.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        )
    ) {
        Column {
            Row {
                Image(
                    painter = painterResource(id = bankCard.img),
                    contentDescription = bankCard.bankName,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                if (isPrimary) {
                    Text(
                        text = "Primary",
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                brush = SolidColor(Color.Transparent),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
            }
            Text(text = bankCard.bankName)
            Text(text = bankCard.accountNumber)
            Spacer(modifier = Modifier.height(30.dp))
            OutlineButton(
                onClick = {},
                text = "Check balance",
                borderColor = primaryColor,
                textColor = primaryColor,
                icon = painterResource(R.drawable.next),
                borderwidth = 2.dp,
                modifier = Modifier,
            )
        }
    }
}

@Composable
fun WalletBalance() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color.Gray)
    ) {
        Row {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(text = "Wallet Balance")
                Row {
                    Text(text = "₹2,36,000.47")
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.eye_closed),
                            "hide",
                            tint = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            OutlineButton(
                onClick = {},
                text = "Add money",
                icon = painterResource(R.drawable.plus),
                modifier = Modifier.padding(15.dp),
                borderColor = primaryColor,
                borderwidth = 1.dp,
                textColor = primaryColor
            )
        }
    }
}

@Composable
fun ProfileTitleBar() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
    ) {
        ProfileContent()
    }
}

@Composable
fun ProfileContent() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfilePicture(img = R.drawable.profile_picture, size = 60)
        Column {
            Text(text = "Thomas Shelby")
            Row {
                Image(painter = painterResource(R.drawable.upi), contentDescription = "UPI")
                Text(text = " 9876543210@qpay")
                Image(painter = painterResource(R.drawable.copy), contentDescription = "Copy")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Image(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Next"
            )
        }
    }
}