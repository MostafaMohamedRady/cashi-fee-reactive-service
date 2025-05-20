//package com.cashi.feeservice.service
//
//import com.cashi.feeservice.dto.TransactionRequest
//import com.cashi.feeservice.repository.TransactionFeeRepository
//import com.cashi.feeservice.validator.TransactionValidator
//import io.mockk.every
//import io.mockk.just
//import io.mockk.mockk
//import io.mockk.runs
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import java.time.LocalDateTime
//import kotlin.test.assertEquals
//
//class FeeCalculatorServiceTest {
//
//    private lateinit var feeCalculatorService: FeeCalculatorService
//    private val repo = mockk<TransactionFeeRepository>()
//    private var validator = mockk<TransactionValidator>()
//
//    @BeforeEach
//    fun setUp() {
//        feeCalculatorService = FeeCalculatorService(repo, validator)
//    }
//
//    private fun buildRequest(
//        type: String = "Mobile Top Up",
//        amount: Double = 200.0,
//        state: String = "SETTLED - PENDING FEE"
//    ): TransactionRequest {
//        return TransactionRequest(
//            transactionId = "tx001",
//            type = type,
//            amount = amount,
//            asset = "USD",
//            assetType = "CASH",
//            state = state,
//            createdAt = LocalDateTime.of(2024, 1, 1, 12, 0)
//        )
//    }
//
//    @Test
//    fun `should calculate correct fee for Mobile Top Up`() {
//        val request = buildRequest()
//
//        every { validator.validateAmount(any()) } just  runs
//        every { validator.validateState(any()) } just runs
//
//        val response = feeCalculatorService.calculateFee(request)
//
//        assertEquals("tx001", response.transactionId)
//    }
//
//    @Test
//    fun `should calculate correct fee for International Transfer`() {
//        val request = buildRequest(type = "International transfer", amount = 100.0)
//
//        every { validator.validateAmount(any()) } just  runs
//        every { validator.validateState(any()) } just runs
//
//        val response = feeCalculatorService.calculateFee(request)
//
//        assertEquals(0.0015, response.rate)
//        assertEquals(0.15, response.fee)
//    }
//
//
//}