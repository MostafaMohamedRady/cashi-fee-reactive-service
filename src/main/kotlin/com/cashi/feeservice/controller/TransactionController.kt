package com.cashi.feeservice.controller

import com.cashi.feeservice.dto.TransactionRequest
import com.cashi.feeservice.dto.TransactionResponse
import com.cashi.feeservice.service.TransactionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v2/transaction")
@Tag(name = "Transaction Fee", description = "Calculate and store transaction fees")
class TransactionController(
    private val transactionService: TransactionService,
) {

    @PostMapping("/fee")
    @Tag(name = "Transaction Fee", description = "Calculate and store transaction fees")
    @Operation(summary = "Calculate fee", description = "Calculates the transaction fee and stores it")
    suspend fun calculateFee(@Valid @RequestBody request: TransactionRequest): TransactionResponse {
        return transactionService.initTransaction(request)
    }
}
