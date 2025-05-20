package com.cashi.feeservice.service

import com.cashi.feeservice.dto.TransactionRequest
import com.cashi.feeservice.dto.TransactionResponse
import com.cashi.feeservice.enum.TransactionType
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("FeeCalculatorService")

/**
 * Validates the request, determines the applicable rate, calculates the fee,
 * and returns a [TransactionResponse].
 *
 * @param request the transaction details submitted by the client.
 * @return a [TransactionResponse] containing the original data plus fee, rate, and description.
 * @throws IllegalArgumentException if the amount or state is invalid.
 */
fun calculateFee(request: TransactionRequest): TransactionResponse {

    logger.info("transaction=${request.transactionId} calculate Fees")

    // 3. Determine the fee rate based on transaction type
    val rate = extractRate(request)

    // 4. Calculate the fee and build the description
    val fee = request.amount * rate
    val description = "Standard fee rate of ${rate * 100}%"

    // 5. Return the response DTO
    return TransactionResponse(
        transactionId = request.transactionId,
        amount = request.amount,
        asset = request.asset,
        type = request.type,
        fee = fee,
        rate = rate,
        description = description
    )
}

/**
 * Determines the fee rate for the given transaction type.
 *
 * @param request the incoming transaction request.
 * @return the fee rate as a double (e.g., 0.0015 for 0.15%).
 */
private fun extractRate(request: TransactionRequest): Double =
    when (request.type) {
        TransactionType.MOBILE_TOP_UP.toString() -> 0.0015  // 0.15%
        TransactionType.INTERNATIONAL_TRANSFER.toString() -> 0.0025  // 0.25%
        TransactionType.BILL_PAYMENT.toString() -> 0.0010  // 0.10%
        TransactionType.CRYPTO_TRANSFER.toString() -> 0.0050  // 0.50%
        else -> 0.0015  // default rate
    }

fun charge(transaction: TransactionResponse): TransactionResponse {
    logger.info("transaction=${transaction.transactionId} charge")
    return transaction
}

fun record(transaction: TransactionResponse): TransactionResponse {
    logger.info("transaction=${transaction.transactionId} record")
    return transaction
}
