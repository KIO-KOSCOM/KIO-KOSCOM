package com.byld.koscomloanalgorithm.domain.response

data class LoanLimitResponse(
    var bankName: String? = null,
    var productName: String? = null,
    var loanEnable: Boolean? = null,
    var loanEnableLimit: Long? = null,
    var loanEnableRate: Double? = null,
    var loanRateType: String? = null,
    var loanEnablePeriod: Long? = null,
)
