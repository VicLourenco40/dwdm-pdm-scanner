package com.viclourenco.ticketscanner.network

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseResponse (
    val purchase: Purchase
)