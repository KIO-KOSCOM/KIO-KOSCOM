package com.byld.koscomloanalgorithm.service

import com.byld.koscomloanalgorithm.domain.entity.LoanEnableLimit
import com.byld.koscomloanalgorithm.domain.service.ApplicationContextService
import com.byld.koscomloanalgorithm.domain.service.ApplicationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class ApplicationServiceTest {
    private val applicationContextService: ApplicationContextService = mock(ApplicationContextService::class.java)

    private val applicationServiceTest = ApplicationService(applicationContextService)

    @Test
    fun applicationServiceTest0() {
        // make LoanEnableLimit random value
        val loanEnableLimit0 =
            LoanEnableLimit().apply {
                loanEnableRate = 10.0
                loanEnableLimit = 10000000
            }

        val loanEnableLimit1 =
            LoanEnableLimit().apply {
                loanEnableRate = 11.0
                loanEnableLimit = 12000000
            }

        val loanEnableLimit2 =
            LoanEnableLimit().apply {
                loanEnableRate = 10.0
                loanEnableLimit = 12000000
            }

        val loanEnableLimit3 =
            LoanEnableLimit().apply {
                loanEnableRate = 11.0
                loanEnableLimit = 10000000
            }

        val loanEnableProductList = listOf(loanEnableLimit0, loanEnableLimit1, loanEnableLimit2, loanEnableLimit3)
        println("Before sorting: \n${loanEnableProductList.parseToString()}")
        println("After sorting By Rate: \n${applicationServiceTest.sortingLoanEnableProductByRate(loanEnableProductList).parseToString()}")
        println(
            "After sorting By Limit: \n${applicationServiceTest.sortingLoanEnableProductByLimit(loanEnableProductList).parseToString()}",
        )
    }

    private fun List<LoanEnableLimit>.parseToString(): String {
        val result = StringBuilder()
        for (loanEnableProduct in this) {
            result.append("loanEnableRate: ${loanEnableProduct.loanEnableRate}, loanEnableLimit: ${loanEnableProduct.loanEnableLimit}\n")
        }
        return result.toString()
    }
}
