package com.cashi.feeservice.dto

import com.cashi.feeservice.util.LocalDateTimeSerializer
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@Schema(description = "Transaction input payload for fee calculation")
data class TransactionRequest(
    @Schema(description = "Unique ID for the transaction", example = "txn_001")
    @JsonAlias("transaction_id")
    @field:NotBlank val transactionId: String,

    @Schema(description = "Transaction amount", example = "1000.0")
    @Contextual @field:Positive val amount: Double,

    @Schema(description = "Currency of the transaction", example = "USD")
    @field:NotBlank val asset: String,

    @Schema(description = "Type of asset (e.g., FIAT, CRYPTO)", example = "FIAT")
    @JsonProperty("asset_type")
    @field:NotBlank val assetType: String,

    @Schema(description = "Transaction type (e.g., Mobile Top Up)", example = "Mobile Top Up")
    @field:NotBlank val type: String,

    @Schema(description = "Transaction state", example = "SETTLED - PENDING FEE")
    @field:NotBlank val state: String,

    @Schema(description = "Creation date of the transaction", example = "2023-08-30T15:42:17.610059")
    @JsonProperty("created_at")
    @Serializable(with = LocalDateTimeSerializer::class)
    @Contextual @field:NotNull val createdAt: LocalDateTime
)
