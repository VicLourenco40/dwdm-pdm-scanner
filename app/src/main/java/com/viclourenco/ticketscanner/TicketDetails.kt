package com.viclourenco.ticketscanner

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viclourenco.ticketscanner.network.Purchase

@Composable
fun TicketDetails(
    purchase: Purchase,
    modifier: Modifier = Modifier
) {
    var expandedDetails by rememberSaveable { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().animateContentSize()
    ) {
        Button(onClick = {expandedDetails = !expandedDetails}) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.details),
                    fontSize = 20.sp
                )
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