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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
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
                    if (purchase == null) {
                        "Ticket Scanner"
                    } else {
                        purchase.ticket.event.name
                    },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Icon(
                imageVector =
                    if (purchase == null) {
                        if (invalidTicket) {
                            Icons.Outlined.ErrorOutline
                        } else {
                            Icons.Outlined.Circle
                        }
                    } else {
                        if (purchase.checkedInAt == null) {
                            Icons.Outlined.CheckCircle
                        } else {
                            Icons.Outlined.Cancel
                        }
                    },
                contentDescription = "Icon",
                modifier = Modifier.size(160.dp)
            )
            Text(
                text =
                    if (purchase == null) {
                        if (invalidTicket) {
                            "Invalid Ticket"
                        } else {
                            "Ready to Scan"
                        }
                    } else {
                        if (purchase.checkedInAt == null) {
                            "Valid Ticket"
                        } else {
                            "Already Scanned"
                        }
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
                        text = "Details",
                        fontSize = 20.sp
                    )
                    IconButton(onClick = {expandedDetails = !expandedDetails}) {
                        Icon(
                            imageVector = if (expandedDetails) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = "Icon"
                        )
                    }
                }
                if (expandedDetails) {
                    Column {
                        Text(
                            text = "Ticket",
                            fontSize = 18.sp
                        )
                        Text("Type: ${purchase.ticket.type}")
                        Text("Code: ${purchase.ticketCode}")
                        Text("Purchased at: ${purchase.purchasedAt}")
                        if (purchase.checkedInAt != null) {
                            Text("Checked-in at: ${purchase.checkedInAt}")
                        }
                        Text(
                            text = "Customer",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text("Name: ${purchase.customer.name}")
                        Text("Email: ${purchase.customer.email}")
                        Text("Phone number: ${purchase.customer.phoneNumber}")
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
                onClick = {}
            ) {
                Text(
                    text = "Check-in",
                    fontSize = 24.sp
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CenterFocusWeak,
                    contentDescription = "Icon",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}