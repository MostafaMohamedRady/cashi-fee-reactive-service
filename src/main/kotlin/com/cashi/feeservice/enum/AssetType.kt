package com.cashi.feeservice.enum

import kotlinx.serialization.Serializable


@Serializable
enum class AssetType {
    FIAT,
    CRYPTO,
    COMMODITY,
    STOCK,
    BOND
}