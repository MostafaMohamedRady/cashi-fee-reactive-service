package com.cashi.feeservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
@Schema(description = "Response after calculating transaction fee")
data class TransactionResponse(

    @Schema(description = "Transaction ID", example = "txn_001")
    val transactionId: String,

    @Schema(description = "Transaction amount", example = "1000.0")
    @Contextual val amount: Double,

    @Schema(description = "Currency", example = "USD")
    val asset: String,

    @Schema(description = "Transaction type", example = "Mobile Top Up")
    val type: String,

    @Schema(description = "Calculated fee", example = "1.5")
    @Contextual val fee: Double,

    @Schema(description = "Applied fee rate", example = "0.0015")
    @Contextual val rate: Double,

    @Schema(description = "Description of fee rule", example = "Standard fee rate of 0.15%")
    val description: String
)
