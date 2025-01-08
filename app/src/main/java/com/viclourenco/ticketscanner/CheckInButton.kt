package com.viclourenco.ticketscanner

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.viclourenco.ticketscanner.network.Purchase

@Composable
fun CheckInButton(
    purchase: Purchase?,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = purchase != null && purchase.checkedInAt == null,
        onClick = {
            if (purchase != null) {
                mainViewModel.getTicketCheckIn(purchase.ticketCode)
            }
        },
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.button_check_in),
            fontSize = 24.sp
        )
    }
}