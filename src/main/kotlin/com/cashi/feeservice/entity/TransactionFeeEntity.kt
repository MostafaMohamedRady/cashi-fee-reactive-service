package com.cashi.feeservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "transaction_fees")
data class TransactionFeeEntity(
    @Id val id: Long?,
    val transactionId: String,

    val amount: Double,
    val asset: String,

    val type: String,
    val fee: Double,
    val rate: Double,
    val description: String,
    val createdAt: LocalDateTime
)
