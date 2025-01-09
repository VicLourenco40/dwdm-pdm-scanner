package com.viclourenco.ticketscanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner

@Composable
fun ScannerButton(
    scanner: GmsBarcodeScanner,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
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
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Icon(
            imageVector = Icons.Filled.CenterFocusWeak,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
    }
}