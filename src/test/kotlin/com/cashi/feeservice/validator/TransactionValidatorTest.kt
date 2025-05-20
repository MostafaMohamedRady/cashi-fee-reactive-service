package com.cashi.feeservice.validator

import com.cashi.feeservice.exception.InvalidAmountException
import com.cashi.feeservice.exception.InvalidStateException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TransactionValidatorTest {

    private val validator = TransactionValidator()

    @Test
    fun `validateAmount should pass for valid amount`() {
        assertDoesNotThrow {
            validator.validateAmount(100.0)
        }
    }

    @Test
    fun `validateAmount should throw exception for zero amount`() {
        val exception = assertThrows(InvalidAmountException::class.java) {
            validator.validateAmount(0.0)
        }
        assertEquals("Invalid amount: must be greater than zero", exception.message)
    }

    @Test
    fun `validateAmount should throw exception for negative amount`() {
        val exception = assertThrows(InvalidAmountException::class.java) {
            validator.validateAmount(-50.0)
        }
        assertEquals("Invalid amount: must be greater than zero", exception.message)
    }

    @Test
    fun `validateState should pass for correct state`() {
        assertDoesNotThrow {
            validator.validateState("SETTLED - PENDING FEE")
        }
    }

    @Test
    fun `validateState should throw exception for incorrect state`() {
        val exception = assertThrows(InvalidStateException::class.java) {
            validator.validateState("PENDING")
        }
        assertEquals("Invalid state: only 'SETTLED - PENDING FEE' is allowed", exception.message)
    }
}