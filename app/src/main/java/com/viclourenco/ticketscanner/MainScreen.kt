package com.viclourenco.ticketscanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(32.dp)
    ) {
        TicketStatus(invalidTicket, purchase, Modifier.padding(bottom = 16.dp))
        if (purchase != null) {
            TicketDetails(purchase)
        }
        Spacer(modifier.weight(1f))
        CheckInButton(purchase, mainViewModel, Modifier.padding(bottom = 32.dp))
        ScannerButton(scanner, mainViewModel)
    }
}