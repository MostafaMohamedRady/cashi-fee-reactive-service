package com.cashi.feeservice.enum

import kotlinx.serialization.Serializable

@Serializable
enum class TransactionStatus {
    FEE_PENDING ,
    FEE_CALCULATED,
    FEE_CHARGED,
    FEE_RECORDED,
    FEE_COMPLETED,
    FEE_FAILED
}