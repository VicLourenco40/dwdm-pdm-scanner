package com.viclourenco.ticketscanner.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Customer (
    val name: String,
    val email: String,
    @SerialName(value = "phone_number")
    val phoneNumber: String
)