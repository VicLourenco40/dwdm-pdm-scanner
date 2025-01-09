package com.viclourenco.ticketscanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Box(modifier = modifier.background(
        if (purchase == null) {
            if (invalidTicket) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.background
        } else {
            if (purchase.checkedInAt == null) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.secondary
        })
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp).fillMaxWidth()
        ) {
            TicketStatus(invalidTicket, purchase, Modifier.padding(bottom = 16.dp))
            if (purchase != null) {
                TicketDetails(purchase)
            }
            Spacer(Modifier.weight(1f))
            CheckInButton(purchase, mainViewModel, Modifier.padding(bottom = 32.dp))
            ScannerButton(scanner, mainViewModel)
        }
    }
}