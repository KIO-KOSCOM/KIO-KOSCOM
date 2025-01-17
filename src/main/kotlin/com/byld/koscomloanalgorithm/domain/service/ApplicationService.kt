package com.byld.koscomloanalgorithm.domain.service

import com.byld.koscomloanalgorithm.domain.entity.LoanEnableLimit
import com.byld.koscomloanalgorithm.domain.entity.Product
import com.byld.koscomloanalgorithm.domain.request.ApplicationRequest
import com.byld.koscomloanalgorithm.domain.response.ApplicationResponse
import com.byld.koscomloanalgorithm.domain.response.LoanLimitResponse
import org.springframework.stereotype.Service

@Service
class ApplicationService(
    private val applicationContextService: ApplicationContextService,
) {
    fun inquireApplication(applicationRequest: ApplicationRequest): ApplicationResponse {
        val applicationId: Long? = applicationContextService.saveApplicationRequest(applicationRequest)
        applicationContextService.saveApplicationInput(applicationId, applicationRequest)

        val productList: List<Product> = applicationContextService.getEnableProduct(applicationRequest.applicationCategory)

        val loanEnableLimitList: List<LoanEnableLimit> =
            productList
                .map { product ->
                    val loanEnableLimit: LoanEnableLimit = makeLoanEnableLimit(applicationId!!, product)
                    loanEnableLimit
                }.filter { it.loanEnable == true }

        applicationContextService.saveLoanEnableLimit(loanEnableLimitList)

        val parseToApplicationResponse =
            loanLimitResponses(loanEnableLimitList)

        val sortedLoanEnableProductByRate: List<LoanLimitResponse> = sortingLoanEnableProductByRate(parseToApplicationResponse)

        val sortedLoanEnableProductByLimit: List<LoanLimitResponse> = sortingLoanEnableProductByLimit(parseToApplicationResponse)

        return ApplicationResponse(
            applicationId = applicationId!!,
            bestRateLoan = sortedLoanEnableProductByRate.first(),
            bestLimitLoan = sortedLoanEnableProductByLimit.first(),
            loanEnableListByRate = sortedLoanEnableProductByRate,
            loanEnableListByLimit = sortedLoanEnableProductByLimit,
        )
    }

    fun makeLoanEnableLimit(
        applicationId: Long,
        product: Product,
    ): LoanEnableLimit {
        val loanEnableLimit: Long = (300..20000).random().toLong() * 10000
        // return random number between 3 and 19.9 rounding up 0.1 type Double
        val loanEnableRate: Double = (30..199).random().toDouble() / 10
        // return random number between 12 and 240
        val loanEnablePeriod: Long = (1..20).random().toLong() * 12
        // return random String between "만기일시상환" and "원리금균등상환" and "원금균등분할상환"
        val repayMethod: String = listOf("만기일시상환", "원리금균등상환", "원금균등분할상환").random()
        val loanEnable: Boolean = listOf(true, false).random()

        return LoanEnableLimit().apply {
            this.applicationId = applicationId
            this.productId = product.id
            this.loanEnable = loanEnable
            this.loanEnableLimit = loanEnableLimit
            this.loanEnableRate = loanEnableRate
            this.loanRateType = repayMethod
            this.loanEnablePeriod = loanEnablePeriod
        }
    }

    fun getApplication(applicationId: Long): ApplicationResponse {
        val loanEnableLimitList: List<LoanEnableLimit> = applicationContextService.getLoanEnableLimitList(applicationId)

        val parseToApplicationResponse = loanLimitResponses(loanEnableLimitList)

        val sortedLoanEnableProductByRate: List<LoanLimitResponse> = sortingLoanEnableProductByRate(parseToApplicationResponse)

        val sortedLoanEnableProductByLimit: List<LoanLimitResponse> = sortingLoanEnableProductByLimit(parseToApplicationResponse)

        return ApplicationResponse(
            applicationId = applicationId,
            bestRateLoan = sortedLoanEnableProductByRate.first(),
            bestLimitLoan = sortedLoanEnableProductByLimit.first(),
            loanEnableListByRate = sortingLoanEnableProductByRate(parseToApplicationResponse),
            loanEnableListByLimit = sortingLoanEnableProductByLimit(parseToApplicationResponse),
        )
    }

    fun getLoanLimit(loanLimitId: Long): LoanLimitResponse {
        val loanEnableLimit: LoanEnableLimit? = applicationContextService.getLoanLimitById(loanLimitId)
        val product = applicationContextService.getProductById(loanEnableLimit?.productId!!)
        val bankName = applicationContextService.getBankById(product?.bankId!!)?.name

        return LoanLimitResponse(
            loanLimitId = loanLimitId,
            bankName = bankName,
            productName = product.productName,
            loanEnable = loanEnableLimit.loanEnable,
            loanEnableLimit = loanEnableLimit.loanEnableLimit,
            loanEnableRate = loanEnableLimit.loanEnableRate,
            loanRateType = loanEnableLimit.loanRateType,
            loanEnablePeriod = loanEnableLimit.loanEnablePeriod,
        )
    }

    private fun loanLimitResponses(loanEnableLimitList: List<LoanEnableLimit>): List<LoanLimitResponse> {
        val parseToApplicationResponse =
            loanEnableLimitList.map {
                val product = applicationContextService.getProductById(it.productId!!)
                val bankName = applicationContextService.getBankById(product?.bankId!!)?.name

                LoanLimitResponse(
                    loanLimitId = it.id,
                    bankName = bankName,
                    productName = product.productName,
                    loanEnable = it.loanEnable,
                    loanEnableLimit = it.loanEnableLimit,
                    loanEnableRate = it.loanEnableRate,
                    loanRateType = it.loanRateType,
                    loanEnablePeriod = it.loanEnablePeriod,
                )
            }
        return parseToApplicationResponse
    }

    fun sortingLoanEnableProductByRate(loanEnableProductList: List<LoanLimitResponse>): List<LoanLimitResponse> =
        loanEnableProductList.sortedWith(
            compareBy<LoanLimitResponse> {
                it.loanEnableRate
            }.thenByDescending { it.loanEnableLimit },
        )

    fun sortingLoanEnableProductByLimit(loanEnableProductList: List<LoanLimitResponse>): List<LoanLimitResponse> =
        loanEnableProductList.sortedWith(
            compareByDescending<LoanLimitResponse> {
                it.loanEnableLimit
            }.thenBy { it.loanEnableRate },
        )
}
