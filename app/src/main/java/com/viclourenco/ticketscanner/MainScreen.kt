package com.viclourenco.ticketscanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    scanner: GmsBarcodeScanner,
    mainViewModel: MainViewModel = viewModel()
) {
    val invalidTicket = mainViewModel.invalidTicket
    val purchase = mainViewModel.purchase
    var expandedDetails by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text =
                    if (purchase == null) stringResource(R.string.app_name)
                    else purchase.ticket.event.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Icon(
                imageVector =
                    if (purchase == null) {
                        if (invalidTicket) Icons.Outlined.ErrorOutline
                        else Icons.Outlined.Circle
                    } else {
                        if (purchase.checkedInAt == null) Icons.Outlined.CheckCircle
                        else Icons.Outlined.Cancel
                    },
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
            Text(
                text =
                    if (purchase == null) {
                        if (invalidTicket) stringResource(R.string.ticket_invalid)
                        else stringResource(R.string.ticket_none)
                    } else {
                        if (purchase.checkedInAt == null) stringResource(R.string.ticket_valid)
                        else stringResource(R.string.ticket_scanned)
                    },
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            if (purchase != null) {
                Text(
                    text = purchase.customer.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (purchase != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.details),
                        fontSize = 20.sp
                    )
                    IconButton(onClick = {expandedDetails = !expandedDetails}) {
                        Icon(
                            imageVector =
                                if (expandedDetails) Icons.Filled.ExpandLess
                                else Icons.Filled.ExpandMore,
                            contentDescription = null
                        )
                    }
                }
                if (expandedDetails) {
                    Column {
                        Text(
                            text = stringResource(R.string.ticket),
                            fontSize = 18.sp
                        )
                        Text("${stringResource(R.string.ticket_type)}: ${purchase.ticket.type}")
                        Text("${stringResource(R.string.ticket_code)}: ${purchase.ticketCode}")
                        Text("${stringResource(R.string.ticket_purchased_at)}: ${purchase.purchasedAt}")
                        if (purchase.checkedInAt != null) {
                            Text("${stringResource(R.string.ticket_checked_in_at)}: ${purchase.checkedInAt}")
                        }
                        Text(
                            text = stringResource(R.string.customer),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text("${stringResource(R.string.customer_name)}: ${purchase.customer.name}")
                        Text("${stringResource(R.string.customer_email)}: ${purchase.customer.email}")
                        Text("${stringResource(R.string.customer_phone)}: ${purchase.customer.phoneNumber}")
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Button(
                enabled = purchase != null && purchase.checkedInAt == null,
                onClick = {
                    if (purchase != null) {
                        mainViewModel.getTicketCheckIn(purchase.ticketCode)
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.button_check_in),
                    fontSize = 24.sp
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    scanner.startScan()
                        .addOnSuccessListener { barcode ->
                            val ticketCode = barcode.rawValue
                            if (ticketCode != null) {
                                mainViewModel.getTicketPurchase(ticketCode.lowercase())
                            }
                        }
                },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CenterFocusWeak,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}