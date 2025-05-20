package com.cashi.feeservice.service

import com.cashi.feeservice.dto.TransactionRequest
import com.cashi.feeservice.dto.TransactionResponse
import com.cashi.feeservice.validator.TransactionValidator
import com.cashi.feeservice.workflow.TransactionWorkflowClient
import dev.restate.client.Client
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class TransactionService(private val transactionValidator: TransactionValidator, private val restateClient: Client) {

    private val logger = LoggerFactory.getLogger(TransactionService::class.java)

    suspend fun initTransaction(request: TransactionRequest): TransactionResponse {

        // Validate the transaction amount
        logger.info("transaction=${request.transactionId} validating amount=${request.amount}")
        transactionValidator.validateAmount(request.amount)

        // Validate the transaction state
        logger.info("transaction=${request.transactionId} validating state=${request.state}")
        transactionValidator.validateState(request.state)

        // TODO invoke the workflow client
        val restateClient: Client = Client.connect("http://localhost:8080")
        TransactionWorkflowClient.fromClient(restateClient, request.transactionId)
            .submit(request)

        return TransactionWorkflowClient.fromClient(restateClient, request.transactionId)
            .workflowHandle()
            .attach()
            .response();
    }
}