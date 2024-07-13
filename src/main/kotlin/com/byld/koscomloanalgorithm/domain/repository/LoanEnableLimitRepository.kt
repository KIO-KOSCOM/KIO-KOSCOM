package com.byld.koscomloanalgorithm.domain.repository

import com.byld.koscomloanalgorithm.domain.entity.LoanEnableLimit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface LoanEnableLimitRepository : JpaRepository<LoanEnableLimit, Long> {
    fun findLoanEnableLimitById(id: Long): LoanEnableLimit?

    fun findByApplicationIdAndInsertTimeIsGreaterThanEqual(
        applicationId: Long,
        insertTime: LocalDateTime,
    ): List<LoanEnableLimit>
}
