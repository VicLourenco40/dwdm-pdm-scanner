package com.viclourenco.ticketscanner.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Purchase (
    @SerialName(value = "ticket_code")
    val ticketCode: String,
    @SerialName(value = "purchased_at")
    val purchasedAt: String,
    @SerialName(value = "checked_in_at")
    val checkedInAt: String? = null,
    val ticket: Ticket,
    val customer: Customer
)