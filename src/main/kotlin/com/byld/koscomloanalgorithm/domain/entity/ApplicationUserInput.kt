package com.byld.koscomloanalgorithm.domain.entity

import com.byld.koscomloanalgorithm.domain.enum.EmploymentType
import com.byld.koscomloanalgorithm.domain.enum.IncomeType
import com.byld.koscomloanalgorithm.domain.enum.LoanPurpose
import com.byld.koscomloanalgorithm.domain.request.ApplicationUserInputRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "application_userinput")
class ApplicationUserInput {
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

    @Comment("주민등록번호")
    @Column(name = "resident_enroll_number")
    var residentEnrollNumber: String? = null

    @Comment("이름")
    @Column(name = "name")
    var name: String? = null

    @Comment("전화번호")
    @Column(name = "phone_number")
    var phoneNumber: String? = null

    @Comment("연소득")
    @Column(name = "yearly_income")
    var yearlyIncome: Long? = null

    @Comment("소득형태")
    @Column(name = "income_type")
    @Enumerated(EnumType.STRING)
    var incomeType: IncomeType? = null

    @Comment("고용형태")
    @Column(name = "employment_type")
    @Enumerated(EnumType.STRING)
    var employmentType: EmploymentType? = null

    @Comment("회사 입사일")
    @Column(name = "company_enter_month")
    var companyEnterMonth: String? = null

    @Comment("회사 사업자번호")
    @Column(name = "company_biz_number")
    var companyBizNumber: String? = null

    @Comment("대출 목적")
    @Column(name = "loan_purpose")
    @Enumerated(EnumType.STRING)
    var loanPurpose: LoanPurpose? = null

    @Comment("차량 번호")
    @Column(name = "car_license_number")
    var carLicenseNumber: String? = null

    fun toEntity(
        applicationId: Long?,
        applicationUserInputRequest: ApplicationUserInputRequest,
    ): ApplicationUserInput {
        this.applicationId = applicationId
        this.residentEnrollNumber = applicationUserInputRequest.residentEnrollNumber
        this.name = applicationUserInputRequest.name
        this.phoneNumber = applicationUserInputRequest.phoneNumber
        this.yearlyIncome = applicationUserInputRequest.yearlyIncome
        this.incomeType = applicationUserInputRequest.incomeType
        this.employmentType = applicationUserInputRequest.employmentType
        this.companyEnterMonth = applicationUserInputRequest.companyEnterMonth
        this.companyBizNumber = applicationUserInputRequest.companyBizNumber
        this.loanPurpose = applicationUserInputRequest.loanPurpose
        this.carLicenseNumber = applicationUserInputRequest.carLicenseNumber
        return this
    }
}
