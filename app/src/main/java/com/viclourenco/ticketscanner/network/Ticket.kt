package com.viclourenco.ticketscanner.network

import kotlinx.serialization.Serializable

@Serializable
data class Ticket (
    val type: String,
    val event: Event
)