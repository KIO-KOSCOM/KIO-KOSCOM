package com.byld.koscomloanalgorithm.domain.service

import com.byld.koscomloanalgorithm.domain.entity.Application
import com.byld.koscomloanalgorithm.domain.entity.ApplicationUserInput
import com.byld.koscomloanalgorithm.domain.entity.Bank
import com.byld.koscomloanalgorithm.domain.entity.LoanEnableLimit
import com.byld.koscomloanalgorithm.domain.entity.MortgageUserInput
import com.byld.koscomloanalgorithm.domain.entity.Product
import com.byld.koscomloanalgorithm.domain.enum.LoanPurpose
import com.byld.koscomloanalgorithm.domain.repository.ApplicationRepository
import com.byld.koscomloanalgorithm.domain.repository.ApplicationUserInputRepository
import com.byld.koscomloanalgorithm.domain.repository.BankRepository
import com.byld.koscomloanalgorithm.domain.repository.LoanEnableLimitRepository
import com.byld.koscomloanalgorithm.domain.repository.MortgageUserInputRepository
import com.byld.koscomloanalgorithm.domain.repository.ProductRepository
import com.byld.koscomloanalgorithm.domain.request.ApplicationRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ApplicationContextService(
    private val applicationRepository: ApplicationRepository,
    private val applicationUserInputRepository: ApplicationUserInputRepository,
    private val mortgageUserInputRepository: MortgageUserInputRepository,
    private val bankRepository: BankRepository,
    private val productRepository: ProductRepository,
    private val loanEnableLimitRepository: LoanEnableLimitRepository,
) {
    @Transactional
    fun saveApplicationRequest(applicationRequest: ApplicationRequest): Long? {
        val application: Application = Application().toEntity(applicationRequest)
        return applicationRepository.save(application).id
    }

    @Transactional
    fun saveApplicationInput(
        applicationId: Long?,
        applicationRequest: ApplicationRequest,
    ) {
        val applicationUserInput: ApplicationUserInput =
            ApplicationUserInput().toEntity(
                applicationId,
                applicationRequest.applicationUserInputRequest,
            )
        applicationUserInputRepository.save(applicationUserInput)

        if (applicationRequest.applicationCategory == LoanPurpose.MORTGAGE) {
            val applicationMortgageEntity: MortgageUserInput =
                MortgageUserInput().toEntity(
                    applicationId,
                    applicationRequest.mortgageRequest,
                )
            mortgageUserInputRepository.save(applicationMortgageEntity)
        }
    }

    fun getBankById(id: Long): Bank? {
        bankRepository.findBankById(id).let { return it }
    }

    fun getProductById(id: Long): Product? {
        productRepository.findProductById(id).let { return it }
    }

    fun getLoanLimitById(id: Long): LoanEnableLimit? {
        loanEnableLimitRepository.findLoanEnableLimitById(id).let { return it }
    }

    fun getEnableProduct(applicationCategory: LoanPurpose?): List<Product> {
        productRepository
            .findByProductCategoryIsAndCallEnableIsTrue(applicationCategory)
            .let { return it }
    }

    fun saveLoanEnableLimit(loanEnableLimitList: List<LoanEnableLimit>) {
        loanEnableLimitRepository.saveAll(loanEnableLimitList)
    }

    fun getLoanEnableLimitList(applicationId: Long): List<LoanEnableLimit> {
        val localDateTimeToday: LocalDateTime = LocalDate.now().atStartOfDay()
        loanEnableLimitRepository.findByApplicationIdAndInsertTimeIsGreaterThanEqual(applicationId, localDateTimeToday).let { return it }
    }
}
