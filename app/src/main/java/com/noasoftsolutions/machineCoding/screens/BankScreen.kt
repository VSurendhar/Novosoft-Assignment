package com.noasoftsolutions.machineCoding.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noasoftsolutions.machineCoding.R
import com.noasoftsolutions.machineCoding.ui.theme.cardBorderColor
import com.noasoftsolutions.machineCoding.ui.theme.itemHeadingGray
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

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
                .background(cardBorderColor)
        )

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

    val tabs = listOf("Accounts", "Cards")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        tabs.forEach { tab ->

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTabSelected(tab) }
                    .padding(vertical = 4.dp)) {

                Text(
                    text = tab,
                    color = if (tab == selectedTab) Color.Black else Color.Gray,
                    fontWeight = if (tab == selectedTab) FontWeight.Bold else FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(
                            if (tab == selectedTab) primaryColor else Color.Gray,
                            shape = RoundedCornerShape(1.dp)
                        )
                )
            }
        }
    }

}


@Composable
fun CardsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "My cards",
            color = Color.Gray,
            fontSize = 15.sp
        )

        CreditCard()

        Spacer(modifier = Modifier.height(15.dp))

        CardDetails()

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(30.dp)
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
fun CardDetails() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 5.dp)
            .border(1.dp, cardBorderColor, RoundedCornerShape(30.dp)), colors = CardColors(
            containerColor = Color.Unspecified,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        )
    ) {
        Box(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        ) {
            Column {
                CardItem("Card number", "9846543210123456", R.drawable.copy)
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .background(cardBorderColor)
                )
                CardItem("Expiry", "09/30", R.drawable.copy)
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .background(cardBorderColor)
                )
                CardItem("Security code (CVV)", "***", R.drawable.eye_closed)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Composable
fun CardItem(heading: String, value: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            Text(text = heading, fontWeight = FontWeight.W200, color = itemHeadingGray)
            Text(text = value, fontWeight = FontWeight.W600)
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable { }
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                painter = painterResource(icon),
                contentDescription = "Copy",
                tint = primaryColor
            )
        }

        Spacer(modifier = Modifier.width(30.dp))

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreditCard() {

    val context = LocalContext.current
    val inputStream = context.resources.openRawResource(R.raw.card_background)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .offset(y = (-80).dp)
                .clip(RectangleShape)
        ) {

            Canvas(modifier = Modifier.fillMaxSize()) {

                val clipRectWidth = size.width * 1.5
                val clipRectHeight = size.height * 3.4

                val startX = 50f
                val startY = 130f

                drawImage(
                    image = bitmap.asImageBitmap(),
                    srcSize = IntSize(bitmap.width, bitmap.height),
                    srcOffset = IntOffset(startX.toInt(), startY.toInt()),
                    dstSize = IntSize(clipRectWidth.toInt(), clipRectHeight.toInt())
                )

            }
        }



        Image(
            modifier = Modifier
                .fillMaxHeight(.3f)
                .offset(x = (130).dp, y = (-80).dp),
            painter = painterResource(R.drawable.sbi_card),
            contentDescription = "SBI Card"
        )

        // gold card

        Image(
            modifier = Modifier
                .fillMaxHeight(.15f)
                .offset(x = (-100).dp, y = (-10).dp)
                .rotate(270f),
            bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.gold))
                .asImageBitmap(),
            contentDescription = "Card"
        )

//        wifi

        Image(
            modifier = Modifier
                .fillMaxHeight(.3f)
                .offset(x = (-150).dp, y = (-80).dp),
            painter = painterResource(R.drawable.wifi),
            contentDescription = "Wifi"
        )

        CardTextDetails(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 140.dp)
        )

    }

}

@Composable
fun CardTextDetails(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "EXP. DATE  09/30", color = Color.Gray.copy(0.8f)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "SECURITY CODE  920",
                color = Color.Gray.copy(0.8f),
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "8174 5300 0364 1148",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(R.drawable.visa),
                contentDescription = "Card"
            )

        }

    }
}

@Composable
fun AppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bank", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.Black
        )
    }
}
