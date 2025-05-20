package com.cashi.feeservice.validator

import com.cashi.feeservice.exception.InvalidAmountException
import com.cashi.feeservice.exception.InvalidStateException
import org.springframework.stereotype.Component

@Component
class TransactionValidator {

    fun validateAmount(amount: Double) {
        if (amount <= 0.0) {
            throw InvalidAmountException("Invalid amount: must be greater than zero")
        }
    }

    fun validateState(state: String) {
        if (state != "SETTLED - PENDING FEE") {
            throw InvalidStateException("Invalid state: only 'SETTLED - PENDING FEE' is allowed")
        }
    }
}