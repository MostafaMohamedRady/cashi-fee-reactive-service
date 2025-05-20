package com.cashi.feeservice.workflow

import com.cashi.feeservice.dto.TransactionRequest
import com.cashi.feeservice.dto.TransactionResponse
import com.cashi.feeservice.enum.TransactionStatus
import com.cashi.feeservice.service.calculateFee
import com.cashi.feeservice.service.charge
import com.cashi.feeservice.service.record
import dev.restate.sdk.annotation.Workflow
import dev.restate.sdk.common.TerminalException
import dev.restate.sdk.kotlin.WorkflowContext
import dev.restate.sdk.kotlin.runBlock
import dev.restate.sdk.kotlin.stateKey
import org.slf4j.LoggerFactory

@Workflow
class TransactionWorkflow {
    companion object {
        private val STATUS = stateKey<TransactionStatus>("transaction-status")
    }

    private val logger = LoggerFactory.getLogger("FeesWorkflow")

    @Workflow
    suspend fun add(ctx: WorkflowContext, request: TransactionRequest): TransactionResponse {
        val compensations: MutableList<suspend () -> Unit> = mutableListOf()

        logger.info("Workflow Started transaction: ${request.transactionId}")

        try {
            ctx.set(STATUS, TransactionStatus.FEE_PENDING)

            val response = ctx.runBlock { calculateFee(request) }
            // TODO Add Rollback for calculateFee compensations.add {  }
            ctx.set(STATUS, TransactionStatus.FEE_CALCULATED)

            val chargeResponse = ctx.runBlock { charge(response) }
            ctx.set(STATUS, TransactionStatus.FEE_CHARGED)

            val recordResponse = ctx.runBlock { record(chargeResponse) }
            ctx.set(STATUS, TransactionStatus.FEE_RECORDED)

            ctx.set(STATUS, TransactionStatus.FEE_COMPLETED)
            return recordResponse;
        } catch (e: TerminalException) {
            compensations.reversed().forEach { ctx.runBlock { it() } }
            ctx.set(STATUS, TransactionStatus.FEE_FAILED)
            throw TerminalException("Subscription failed")
        }
    }

}

