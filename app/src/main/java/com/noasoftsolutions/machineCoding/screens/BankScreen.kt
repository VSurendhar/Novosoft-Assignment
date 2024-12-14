package com.noasoftsolutions.machineCoding.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noasoftsolutions.machineCoding.R
import com.noasoftsolutions.machineCoding.ui.theme.backgroundColor
import com.noasoftsolutions.machineCoding.ui.theme.primaryColor

@Composable
fun BankScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        AppBar()
        BankScreenContent()
    }
}

@Composable
fun BankScreenContent() {

    var selectedTab by remember { mutableStateOf("Accounts") }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTab = selectedTab) { tab ->
            selectedTab = tab
        }

        when (selectedTab) {
            "Accounts" -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "This page is not in the design file",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }

            "Cards" -> CardsContent()
        }
    }
}

@Composable
fun TabRow(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val tabs = listOf("Accounts", "Cards")
        tabs.forEach { tab ->
            TextButton(
                onClick = { onTabSelected(tab) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (selectedTab == tab) primaryColor else Color.DarkGray
                ),
                modifier = Modifier.drawBehind {
                    if (selectedTab == tab) {
                        val strokeWidth = 4.dp.toPx()
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            color = primaryColor,
                            start = androidx.compose.ui.geometry.Offset(0f, y),
                            end = androidx.compose.ui.geometry.Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    }
                }
            ) {
                Text(text = tab, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CardsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CreditCard()

        Spacer(modifier = Modifier.height(20.dp))

        CardDetailItem("Card Number", "1234 5678 9876 5432", R.drawable.copy, R.drawable.eye_closed)
        Spacer(modifier = Modifier.height(16.dp))
        CardDetailItem("Expiry Date", "12/25", R.drawable.copy, R.drawable.eye_closed)
        Spacer(modifier = Modifier.height(16.dp))
        CardDetailItem("Security Code (CVV)", "123", R.drawable.copy, R.drawable.eye_closed)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.card),
                contentDescription = "Add Card",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add New Card", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CreditCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(primaryColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Bank Name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "1234 5678 9876 5432",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Card Holder", color = Color.White, fontSize = 14.sp)
                Text(text = "Valid Thru: 12/25", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun CardDetailItem(title: String, value: String, copyIcon: Int, hideIcon: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    style = TextStyle(color = Color.Black)
                )
            }
            Row {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = copyIcon),
                        contentDescription = "Copy",
                        tint = Color.Black
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = hideIcon),
                        contentDescription = "Hide",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun AppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Bank",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}
