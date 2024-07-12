package com.byld.koscomloanalgorithm.domain.entity

import com.byld.koscomloanalgorithm.domain.response.LoanLimitResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Table(name = "loan_enable_limit")
@Entity
class LoanEnableLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    var id: Long? = null

    @CreationTimestamp
    var insertTime: LocalDateTime? = null

    @UpdateTimestamp
    var updateTime: LocalDateTime? = null

    @Comment("application_id")
    @Column(name = "application_id")
    var applicationId: Long? = null

    @Comment("product_id")
    @Column(name = "product_id")
    var productId: Long? = null

    @Comment("대출 가능 여부")
    @Column(name = "loan_enable")
    var loanEnable: Boolean? = null

    @Comment("대출 가능 한도")
    @Column(name = "loan_enable_limit")
    var loanEnableLimit: Long? = null

    @Comment("대출 가능 금리")
    @Column(name = "loan_enable_rate")
    var loanEnableRate: Double? = null

    @Comment("대출 금리 종류")
    @Column(name = "loan_rate_type")
    var loanRateType: String? = null

    @Comment("대출 가능 기간")
    @Column(name = "loan_enable_period")
    var loanEnablePeriod: Long? = null

    fun toDto(): LoanLimitResponse =
        LoanLimitResponse(
            loanEnable = this.loanEnable,
            loanEnableLimit = this.loanEnableLimit,
            loanEnableRate = this.loanEnableRate,
            loanRateType = this.loanRateType,
            loanEnablePeriod = this.loanEnablePeriod,
        )
}
