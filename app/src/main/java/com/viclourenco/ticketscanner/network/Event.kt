package com.viclourenco.ticketscanner.network

import kotlinx.serialization.Serializable

@Serializable
data class Event (
    val name: String
)