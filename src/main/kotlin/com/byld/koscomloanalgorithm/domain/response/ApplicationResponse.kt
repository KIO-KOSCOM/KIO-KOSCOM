package com.byld.koscomloanalgorithm.domain.response

data class ApplicationResponse(
    val applicationId: Long,
    val bestRateLoan: LoanLimitResponse,
    val bestLimitLoan: LoanLimitResponse,
    val loanEnableListByRate: List<LoanLimitResponse>,
    val loanEnableListByLimit: List<LoanLimitResponse>,
)
