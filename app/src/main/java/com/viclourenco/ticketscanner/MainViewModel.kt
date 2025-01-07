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

    init {
        getTicketPurchase("01jgepy60fxhghnaxz3z0v11qq")
    }

    fun getTicketPurchase(ticketCode: String) {
        viewModelScope.launch {
            try {
                val purchaseResponse = TicketsApi.retrofitService.getPurchase(ticketCode)
                purchase = purchaseResponse.purchase
                invalidTicket = false
            } catch(error: HttpException) {
                purchase = null
                invalidTicket = true
            } catch (error: IOException) {
                //
            }
        }
    }
}