package com.viclourenco.ticketscanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viclourenco.ticketscanner.network.Purchase

@Composable
fun TicketStatus(
    invalidTicket: Boolean,
    purchase: Purchase?,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text =
                if (purchase == null) stringResource(R.string.app_name)
                else purchase.ticket.event.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Status(invalidTicket, purchase, Modifier.padding(bottom = 32.dp))
        if (purchase != null) {
            Text(
                text = purchase.customer.name,
                fontSize = 20.sp
            )
        }
    }
}