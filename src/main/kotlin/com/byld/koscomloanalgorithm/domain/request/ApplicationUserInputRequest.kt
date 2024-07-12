package com.byld.koscomloanalgorithm.domain.request

import com.byld.koscomloanalgorithm.domain.enum.EmploymentType
import com.byld.koscomloanalgorithm.domain.enum.HouseMortgagePurpose
import com.byld.koscomloanalgorithm.domain.enum.IncomeType
import com.byld.koscomloanalgorithm.domain.enum.LoanPurpose

data class ApplicationRequest(
    val applicationCategory: LoanPurpose? = LoanPurpose.NONE,
    val applicationStatus: String? = null,
    val applicationCertification: String? = null,
    val applicationUserInputRequest: ApplicationUserInputRequest,
    val mortgageRequest: ApplicationMortgageRequest?,
    val dummyType: Boolean? = null,
)

data class ApplicationUserInputRequest(
    val residentEnrollNumber: String?,
    val name: String?,
    val phoneNumber: String?,
    val yearlyIncome: Long?,
    val incomeType: IncomeType?,
    val employmentType: EmploymentType?,
    val companyEnterMonth: String?,
    val companyBizNumber: String?,
    val loanPurpose: LoanPurpose?,
    val carLicenseNumber: String?,
)

data class ApplicationMortgageRequest(
    val apartmentKey: String?,
    val houseMortgagePurpose: HouseMortgagePurpose?,
    val dong: String?,
    val ho: String?,
    val houseDisposalConditionAgreed: Boolean?,
)
