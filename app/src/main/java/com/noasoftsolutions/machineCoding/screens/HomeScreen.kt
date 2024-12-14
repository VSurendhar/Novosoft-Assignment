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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.noasoftsolutions.machineCoding.R
import com.noasoftsolutions.machineCoding.comman.Comman.bankCards
import com.noasoftsolutions.machineCoding.comman.Comman.bobDetails
import com.noasoftsolutions.machineCoding.comman.Comman.cardItems
import com.noasoftsolutions.machineCoding.comman.Comman.receiveMoneyOptions
import com.noasoftsolutions.machineCoding.comman.Comman.sbiDetails
import com.noasoftsolutions.machineCoding.comman.Comman.wallet
import com.noasoftsolutions.machineCoding.model.BankCard
import com.noasoftsolutions.machineCoding.model.BankTitleCardDetail
import com.noasoftsolutions.machineCoding.model.BookingItem
import com.noasoftsolutions.machineCoding.model.User
import com.noasoftsolutions.machineCoding.model.Wallet
import com.noasoftsolutions.machineCoding.ui.theme.lightGreenColor
import com.noasoftsolutions.machineCoding.ui.theme.primaryColor
import com.noasoftsolutions.machineCoding.viewModels.MyViewModel


@Composable
fun HomeScreen(modifier: Modifier, viewModel: MyViewModel) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        TitleBar()
        BankCards(bankCards)
        TicketBooking()
        ReceiveMoney()
        BillPayment()
        RecentTransactions(viewModel)
    }
}

@Composable
fun RecentTransactions(viewModel: MyViewModel) {
    val users = viewModel.users.collectAsState()

    StandardCard {
        Text(
            text = "Recent Transactions",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Spacer(Modifier.height(10.dp))
        LazyRow {
            items(users.value.size) { index ->
                TransactionItem(
                    users.value[index],
                )
                Spacer(Modifier.width(15.dp))
            }
        }
    }
}

@Composable
fun TransactionItem(user: User) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadPicture(user.avatar_url, 40)
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = user.login,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun LoadPicture(url: String, size: Int) {
    AsyncImage(
        model = url,
        contentDescription = "Profile Picture",
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.account_outline),
        error = painterResource(R.drawable.account_outline)
    )
}

@Composable
fun BillPayment() {
    StandardCard {
        Text(
            text = "Bill Payments",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Spacer(Modifier.height(10.dp))
        BillPaymentOptions()
    }
}

@Composable
fun BillPaymentOptions() {
    Column(modifier = Modifier.fillMaxWidth()) {
        cardItems.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { cardItem ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                            .height(50.dp)
                            .clickable { },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White, contentColor = Color.Black
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = cardItem.imageRes),
                                contentDescription = cardItem.label,
                                tint = Color.Unspecified,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = cardItem.label,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReceiveMoney() {
    StandardCard {
        Text(
            text = "Receive Money",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "UPI ID: 9876543210@qpay")
        Spacer(Modifier.height(12.dp))
        ReceiveMoneyOptions()
    }
}

@Composable
fun ReceiveMoneyOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        receiveMoneyOptions.forEach { option ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {}, colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.2f), contentColor = Color.Black
                ), shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = option.icon),
                        contentDescription = option.label,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = option.label, style = TextStyle(fontSize = 15.sp)
                    )
                }
            }
        }

//        IconButton(
//            onClick = {  },
//            modifier = Modifier
//                .size(30.dp)
//                .clip(CircleShape)
//                .border(
//                    width = 1.dp,
//                    color = Color.Gray,
//                    shape = CircleShape
//                )
//        ) {
//            Icon(
//                imageVector = Icons.Default.MoreVert,
//                contentDescription = "Menu",
//                tint = Color.Black,
//                modifier = Modifier.size(30.dp)
//            )
//        }

    }
}


@Composable
fun TicketBooking() {

    val bookingItems = listOf(
        BookingItem(icon = R.drawable.flight, label = "Flight"),
        BookingItem(icon = R.drawable.bus, label = "Bus"),
        BookingItem(icon = R.drawable.train, label = "Train"),
        BookingItem(icon = R.drawable.hotel, label = "Hotel")
    )

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color.Gray),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Ticket Booking", modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                bookingItems.forEach { item ->
                    BookingItemView(item)
                }
            }
        }
    }
}

@Composable
fun BookingItemView(item: BookingItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.wrapContentWidth()
    ) {
        Card(
            modifier = Modifier.size(60.dp), colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.Unspecified,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray
            )
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(id = item.icon), contentDescription = item.label
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.label, color = Color.Black
        )
    }
}


@Composable
fun BankCards(bankCards: List<BankTitleCardDetail>) {
    val selectedListener = remember { mutableIntStateOf(0) }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(250.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color(0xFF42794A), Color(0xFF61CE70)),
                        center = Offset(0.5f, 0f),
                        radius = 1000f
                    )
                )
                .fillMaxSize(),
        ) {
            Column {
                CardTitle(bankCards) {
                    selectedListener.intValue = it
                }
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(lightGreenColor)
                )
                BankSelector(selectedListener.intValue)
            }
        }
    }
}


@Composable
fun BankSelector(selectedItem: Int) {
    when (selectedItem) {
        0 -> QPayWallet(wallet)
        1 -> BankAccountDetails(sbiDetails)
        2 -> BankAccountDetails(bobDetails)
    }
}


@Composable
fun QPayWallet(wallet: Wallet) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Account balance", color = lightGreenColor)
        Row {
            Text(
                text = wallet.accountBalance,
                color = Color.White,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.eye_closed),
                    contentDescription = "hide Amount"
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 20.dp)
        ) {
            OutlineButton(
                onClick = wallet.leftButton,
                text = "Add Money",
                icon = painterResource(id = R.drawable.plus),
                borderwidth = 1.dp,
                borderColor = Color.White,
                modifier = Modifier.weight(1f),
                textColor = Color.White
            )
            Spacer(modifier = Modifier.width(10.dp))
            FilledButton(
                icon = painterResource(id = R.drawable.transfer),
                onClick = wallet.rightButton,
                containerColor = Color.White,
                contentColor = primaryColor,
                text = "Transfer Money",
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
fun BankAccountDetails(bankCard: BankCard) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier.fillMaxWidth()) {

                Column(horizontalAlignment = Alignment.Start) {
                    AccountDetailRow(
                        label = "Account Number",
                        value = bankCard.accountNumber,
                        icon = R.drawable.eye_open
                    )
                    AccountDetailRow(
                        label = "IFSC Code", value = bankCard.ifsc
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column(horizontalAlignment = Alignment.End) {
                    AccountDetailRow(
                        label = "Bank", value = bankCard.bankName
                    )
                    AccountDetailRow(
                        label = "Account Balance",
                        value = bankCard.accountBalance,
                        icon = R.drawable.eye_closed
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlineButton(
                    modifier = Modifier.weight(1f),
                    onClick = bankCard.leftButton,
                    text = "Statement",
                    icon = painterResource(id = R.drawable.document),
                    borderwidth = 1.dp,
                    borderColor = Color.White,
                    textColor = Color.White
                )
                Spacer(modifier = Modifier.width(15.dp))
                FilledButton(
                    modifier = Modifier.weight(1f),
                    onClick = bankCard.rightButton,
                    text = "Transfer",
                    icon = painterResource(id = R.drawable.transfer),
                    containerColor = Color.White,
                    contentColor = primaryColor
                )
            }
        }
    }
}

@Composable
fun AccountDetailRow(
    label: String, value: String, icon: Int? = null, modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Text(
            modifier = modifier, text = label, style = TextStyle(color = lightGreenColor)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = value, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
            icon?.let {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "Show value",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun CardTitle(bankCards: List<BankTitleCardDetail>, onSelect: (Int) -> Unit) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .height(58.dp)
        ) {
            bankCards.forEachIndexed { index, item ->
                val isLast = index == bankCards.size - 1
                CardTitleItem(item, isLast, onClick = { onSelect(index) })
            }
        }
    }
}

@Composable
fun CardTitleItem(bankTitleCardDetail: BankTitleCardDetail, isLast: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BankTitleCard(bankTitleCardDetail)
        if (!isLast) {
            Spacer(
                modifier = Modifier
                    .width(2.dp)
                    .height(70.dp)
                    .background(lightGreenColor)
            )
        }
    }
}


@Composable
fun BankTitleCard(bankTitleCardDetail: BankTitleCardDetail) {
    Row(modifier = Modifier) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(bankTitleCardDetail.img),
            contentDescription = "Bank Card Img",
            modifier = Modifier
                .size(40.dp)
                .padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(text = bankTitleCardDetail.title)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = bankTitleCardDetail.balance,
                style = TextStyle(color = Color.White),
                fontWeight = FontWeight.W500
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
    }
}


@Composable
fun TitleBar() {
    Row(modifier = Modifier.padding(horizontal = 20.dp)) {
        ProfilePicture(R.drawable.profile_picture, 40)
        Spacer(modifier = Modifier.width(10.dp))
        HeaderText()
        Spacer(modifier = Modifier.weight(1f))
        QRCode()
    }
}


@Composable
fun HeaderText() {
    Column(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Text(text = "Hello", style = TextStyle(color = Color.Gray.copy(alpha = 0.7f)))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Thomas Shelby...", style = TextStyle(color = Color.Black))
    }
}


@Composable
fun QRCode() {
    Box(
        modifier = Modifier.shadow(
            2.dp,
            shape = RoundedCornerShape(10.dp),
            spotColor = Color.Black.copy(alpha = 1f),
            ambientColor = Color.Black.copy(alpha = 1f)
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.qr_green),
            contentDescription = "QR Code",
            modifier = Modifier
                .background(Color.White)
                .size(50.dp),
            tint = Color.Unspecified
        )
    }
}


@Composable
fun FilledButton(
    icon: Painter,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    text: String,
    modifier: Modifier
) {
    Button(
        modifier = modifier, onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon, contentDescription = text
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }
    }
}


@Composable
fun OutlineButton(
    onClick: () -> Unit,
    text: String,
    icon: Painter?,
    borderwidth: Dp,
    borderColor: Color,
    textColor: Color,
    modifier: Modifier
) {
    Button(
        modifier = modifier, onClick = onClick, colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Unspecified,
            contentColor = textColor
        ), border = BorderStroke(borderwidth, borderColor)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    painter = icon, contentDescription = text, tint = textColor
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Composable
fun StandardCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color.Gray)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            content()
        }
    }
}

@Composable
fun ProfilePicture(img: Int, size: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = "Profile Picture",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
