package com.cashi.feeservice.repository

import com.cashi.feeservice.entity.TransactionFeeEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface FeeRepository : ReactiveCrudRepository<TransactionFeeEntity, Long>