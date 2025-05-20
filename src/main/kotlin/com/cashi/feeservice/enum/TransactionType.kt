package com.cashi.feeservice.enum

import kotlinx.serialization.Serializable

@Serializable
enum class TransactionType(val displayName: String) {
    MOBILE_TOP_UP("Mobile Top Up"),
    INTERNATIONAL_TRANSFER("International Transfer"),
    BILL_PAYMENT("Bill Payment"),
    CRYPTO_TRANSFER("Crypto Transfer");

    companion object {
        fun fromDisplayName(name: String): TransactionType {
            return entries.firstOrNull { it.displayName.equals(name, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid transaction type: $name")
        }
    }
}