package com.viclourenco.ticketscanner.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckInResponse (
    val message: String? = null,
    @SerialName(value = "checked_in_at")
    val checkedInAt: String? = null,
    val error: String? = null
)