//package com.cashi.feeservice.workflow
//
//import com.cashi.feeservice.dto.TransactionRequest
//import com.cashi.feeservice.dto.TransactionResponse
//import com.cashi.feeservice.service.FeeCalculatorService
//import dev.restate.client.Client
//import dev.restate.sdk.testing.BindService
//import dev.restate.sdk.testing.RestateClient
//import dev.restate.sdk.testing.RestateTest
//import io.kotest.common.runBlocking
//import io.mockk.every
//import io.mockk.mockk
//import kotlinx.coroutines.test.runTest
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Timeout
//import java.time.LocalDateTime
//
//@RestateTest
//class TransactionTest {
//
//    private val mockService = mockk<FeeCalculatorService>()
//
//    @BindService
//    var service = Transaction(mockService)
//    private lateinit var client: TransactionClient.IngressClient
//
//    @BeforeEach
//    fun setup(@RestateClient ingressClient: Client) {
//        client = TransactionClient.fromClient(ingressClient)
//    }
//
//    @Test
//    @Timeout(10)
//    fun processMobileTopUpTransaction() = runTest {
//
//        // Given
//        val request = TransactionRequest(
//            "txn01",
//            1000.0,
//            "CRYPTO",
//            "CRYPTO",
//            "Mobile Top Up",
//            "SETTLED - PENDING FEE", LocalDateTime.now()
//        )
//
//        every { runBlocking { mockService.calculateFee(request) } } returns TransactionResponse(
//            "tx123",
//            1000.0,
//            "asset",
//            "type",
//            0.15,
//            0.0,
//            "desc"
//        )
//        every { runBlocking { mockService.recordFeeCalculation(request, any()) } } returns Unit
//
//        // When
//        val result = client.fee(request)
//
//        // Then
//        assertThat(result.fee).isEqualTo(0.15)
//    }
//
//    @Test
//    @Timeout(10)
//    fun processInternationalTransfer() = runTest {
//        // Given
//        val request = TransactionRequest(
//            "txn01",
//            1000.0,
//            "USD",
//            "FIAT",
//            "International Transfer",
//            "SETTLED - PENDING FEE", LocalDateTime.now()
//        )
//
//        every { runBlocking { mockService.calculateFee(request) } } returns TransactionResponse(
//            "tx123",
//            1000.0,
//            "asset",
//            "type",
//            0.35,
//            0.0,
//            "desc"
//        )
//        every { runBlocking { mockService.recordFeeCalculation(request, any()) } } returns Unit
//
//        // When
//        val result = client.fee(request)
//
//        // Then
//        assertThat(result.fee).isEqualTo(0.35)
//    }
//}