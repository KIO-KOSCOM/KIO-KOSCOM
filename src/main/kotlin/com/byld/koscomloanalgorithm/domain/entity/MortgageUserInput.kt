package com.byld.koscomloanalgorithm.domain.entity

import com.byld.koscomloanalgorithm.domain.enum.HouseMortgagePurpose
import com.byld.koscomloanalgorithm.domain.request.ApplicationMortgageRequest
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
@Table(name = "mortgage_userInput")
class MortgageUserInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @CreationTimestamp
    var insertTime: LocalDateTime? = null

    @UpdateTimestamp
    var updateTime: LocalDateTime? = null

    @Comment("application_id")
    @Column(name = "application_id")
    var applicationId: Long? = null

    @Comment("아파트 고유 키")
    @Column(name = "apartment_key")
    var apartmentKey: String? = null

    @Comment("주택 담보 대출 목적")
    @Column(name = "house_mortgage_purpose")
    @Enumerated(EnumType.STRING)
    var houseMortgagePurpose: HouseMortgagePurpose? = null

    @Comment("동")
    @Column(name = "dong")
    var dong: String? = null

    @Comment("호")
    @Column(name = "ho")
    var ho: String? = null

    @Comment("주택 처분 조건 동의 여부")
    @Column(name = "house_disposal_condition_agreed")
    var houseDisposalConditionAgreed: Boolean? = null

    fun toEntity(
        applicationId: Long?,
        mortgageRequest: ApplicationMortgageRequest?,
    ): MortgageUserInput {
        this.applicationId = applicationId
        this.apartmentKey = mortgageRequest?.apartmentKey
        this.houseMortgagePurpose = mortgageRequest?.houseMortgagePurpose
        this.dong = mortgageRequest?.dong
        this.ho = mortgageRequest?.ho
        this.houseDisposalConditionAgreed = mortgageRequest?.houseDisposalConditionAgreed
        return this
    }
}
