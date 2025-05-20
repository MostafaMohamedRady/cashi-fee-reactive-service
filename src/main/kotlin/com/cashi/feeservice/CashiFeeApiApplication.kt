package com.cashi.feeservice

import com.cashi.feeservice.workflow.TransactionWorkflow
import dev.restate.sdk.http.vertx.RestateHttpServer
import dev.restate.sdk.kotlin.endpoint.endpoint
import dev.restate.sdk.springboot.EnableRestate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRestate
class FeeserviceApplication

fun main(args: Array<String>) {
	RestateHttpServer.listen(endpoint { bind(TransactionWorkflow()) })
	runApplication<FeeserviceApplication>(*args)
}
