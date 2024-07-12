package com.byld.koscomloanalgorithm.domain.repository

import com.byld.koscomloanalgorithm.domain.entity.Bank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository : JpaRepository<Bank, Long> {
    fun findBankById(id: Long): Bank?
}
