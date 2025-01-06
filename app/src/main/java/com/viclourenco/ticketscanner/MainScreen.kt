package com.viclourenco.ticketscanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

object purchase {
    val ticket_code = "01jgepy60fyjga56nr8bd5w61g"
    val purchased_at = "2024-09-04 18:55:08"
    val checked_in_at = "2024-09-05 14:30:15"
    object ticket {
        val type = "VIP"
        object event {
            val name = "Festival Camecípare"
        }
    }
    object customer {
        val name = "Luís Barros"
        val email = "luis.barros@mail.com"
        val phone_number = "+351967584930"
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel()
) {
    var expandedDetails by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = purchase.ticket.event.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Icon(
                imageVector = Icons.Filled.CheckCircleOutline,
                contentDescription = "Icon",
                modifier = Modifier.size(160.dp)
            )
            Text(
                text = "Valid Ticket",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Text(
                text = purchase.customer.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
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
                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Customer",
                            fontSize = 18.sp
                        )
                        Text("Name: ${purchase.customer.name}")
                        Text("Email: ${purchase.customer.email}")
                        Text("Phone number: ${purchase.customer.phone_number}")
                    }
                    Column {
                        Text(
                            text = "Ticket",
                            fontSize = 18.sp
                        )
                        Text("Type: ${purchase.ticket.type}")
                        Text("Code: ${purchase.ticket_code}")
                        Text("Purchased at: ${purchase.purchased_at}")
                        Text("Checked-in at: ${purchase.checked_in_at}")
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
            Button(onClick = {}) {
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