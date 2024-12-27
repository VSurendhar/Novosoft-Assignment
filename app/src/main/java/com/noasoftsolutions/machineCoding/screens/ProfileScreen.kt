package com.noasoftsolutions.machineCoding.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noasoftsolutions.machineCoding.R
import com.noasoftsolutions.machineCoding.comman.Comman.bankAccounts
import com.noasoftsolutions.machineCoding.comman.Comman.helpSupport
import com.noasoftsolutions.machineCoding.comman.Comman.securityOptions
import com.noasoftsolutions.machineCoding.comman.Comman.settingsPreferences
import com.noasoftsolutions.machineCoding.model.BankCard
import com.noasoftsolutions.machineCoding.model.Options
import com.noasoftsolutions.machineCoding.ui.theme.cardBorderColor
import com.noasoftsolutions.machineCoding.ui.theme.itemHeadingGray
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
    StandardCard(
        modifier = Modifier.wrapContentSize(), topSpace = 0.dp
    ) {
        Column(
            modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Receiving money in", color = itemHeadingGray)
            Spacer(modifier = Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.sbi),
                    contentDescription = "sbi Icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = "xxxx 3941")
                    Text(text = "State Bank of India", style = TextStyle(color = Color.Gray))
                }
                Spacer(modifier = Modifier.weight(1f))
                OutlineButton(
                    onClick = {},
                    text = "Manage",
                    icon = painterResource(R.drawable.manage),
                    borderwidth = 1.dp,
                    borderColor = primaryColor,
                    textColor = primaryColor,
                    modifier = Modifier.padding(end = 15.dp),
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(R.drawable.upi), contentDescription = "upi")
                Text(text = "1234567899@qpay")
                Box(modifier = Modifier.size(30.dp)) {
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
}

@Composable
fun CommonCard(
    borderColor: Color = cardBorderColor,
    title: String,
    containerColor: Color = Color.Unspecified,
    items: List<Options>,
    modifier: Modifier = Modifier,
    content: @Composable (Options) -> Unit
) {
    Card(
        modifier = modifier.padding(16.dp), colors = CardColors(
            containerColor = containerColor,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ), shape = RoundedCornerShape(25.dp), border = BorderStroke(1.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = title, color = itemHeadingGray)
            Spacer(modifier = Modifier.height(10.dp))
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
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        content(item)
                    }
                }
                if (items.last() != item) {
                    Spacer(modifier = Modifier.height(10.dp))
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
        borderColor = cardBorderColor,
    ) { item ->
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(item.icon),
                contentDescription = item.primaryText,
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = item.primaryText)
                Text(text = item.secondaryText, color = itemHeadingGray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.next),
                    contentDescription = "Next",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
fun SecurityProtection() {
    CommonCard(
        title = "Security & Protection",
        items = securityOptions,
    ) { item ->
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(item.icon),
                contentDescription = item.primaryText,
                modifier = Modifier.size(25.dp)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = item.primaryText)
                Text(text = item.secondaryText, color = itemHeadingGray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.next),
                    contentDescription = "Next",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
fun HelpSupport() {
    CommonCard(
        title = "Help & Support",
        items = helpSupport,
    ) { item ->
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(item.icon),
                contentDescription = item.primaryText,
                modifier = Modifier.size(25.dp)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = item.primaryText)
                Text(text = item.secondaryText, color = itemHeadingGray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.next),
                    contentDescription = "Next",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
fun BankAccounts() {
    StandardCard(modifier = Modifier.wrapContentSize(), topSpace = 0.dp) {
        Column {
            Text(text = "Bank accounts", color = itemHeadingGray)
            Spacer(modifier = Modifier.height(10.dp))
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
    StandardCard(
        color = Color.White,
        borderColor = Color.Unspecified,
        modifier = Modifier.wrapContentSize(),
        topSpace = 0.dp,
        paddingValues = PaddingValues(all = 0.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = bankCard.img),
                contentDescription = bankCard.bankName,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (isPrimary) {
                Text(
                    text = "Primary", modifier = Modifier
                        .border(
                            width = 2.dp,
                            brush = SolidColor(Color.Transparent),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = bankCard.bankName)
        Text(text = bankCard.accountNumber)
        Spacer(modifier = Modifier.height(15.dp))
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

@Composable
fun WalletBalance() {
    StandardCard(modifier = Modifier.wrapContentSize(), topSpace = 0.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(text = "Wallet Balance", color = itemHeadingGray)
                Row(
                    modifier = Modifier.height(30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                modifier = Modifier.padding(end = 15.dp),
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
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
    ) {
        ProfileContent()
    }
}

@Composable
fun ProfileContent() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfilePicture(img = R.drawable.profile_picture, size = 60)
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = "Thomas Shelby")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(R.drawable.upi), contentDescription = "UPI")
                Text(text = " 9876543210@qpay")
                IconButton(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(20.dp),
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.copy),
                        contentDescription = "Copy"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Image(
                imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "Next"
            )
        }
    }
}
