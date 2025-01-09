package com.viclourenco.ticketscanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viclourenco.ticketscanner.network.Purchase

@Composable
fun Status(
    invalidTicket: Boolean,
    purchase: Purchase?,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
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
            tint = contentColor,
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
            color = contentColor
        )
    }
}