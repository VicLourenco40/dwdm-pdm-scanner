package com.viclourenco.ticketscanner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viclourenco.ticketscanner.network.Purchase
import com.viclourenco.ticketscanner.network.TicketsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {
    var invalidTicket by mutableStateOf(false)
    var purchase: Purchase? by mutableStateOf(null)

    private fun isValidUlid(input: String): Boolean {
        val ulidRegex = "^[0-7A-Z]{26}$".toRegex()
        return ulidRegex.matches(input)
    }

    fun getTicketPurchase(ticketCode: String) {
        if (!isValidUlid(ticketCode)) {
            invalidTicket = true
            return
        }
        viewModelScope.launch {
            try {
                val purchaseResponse = TicketsApi.retrofitService.getPurchase(ticketCode)
                purchase = purchaseResponse.purchase
                invalidTicket = false
            } catch (error: HttpException) {
                purchase = null
                invalidTicket = true
            } catch (error: IOException) {
                //
            }
        }
    }

    fun getTicketCheckIn(ticketCode: String) {
        viewModelScope.launch {
            try {
                TicketsApi.retrofitService.checkIn(ticketCode)
                purchase = null
            } catch (error: HttpException) {
                //
            } catch (error: IOException) {
                //
            }
        }
    }
}