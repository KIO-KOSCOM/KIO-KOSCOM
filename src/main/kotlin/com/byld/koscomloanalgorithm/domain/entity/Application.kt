package com.byld.koscomloanalgorithm.domain.entity

import com.byld.koscomloanalgorithm.domain.enum.LoanPurpose
import com.byld.koscomloanalgorithm.domain.request.ApplicationRequest
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
@Table(name = "application")
class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    var id: Long? = null

    @CreationTimestamp
    var insertTime: LocalDateTime? = null

    @UpdateTimestamp
    var updateTime: LocalDateTime? = null

    @Comment("대출 신청 종류")
    @Column(name = "application_category")
    @Enumerated(EnumType.STRING)
    var applicationCategory: LoanPurpose? = null

    @Comment("대출 신청 상태")
    @Column(name = "application_status")
    var applicationStatus: String? = null

    @Comment("대출 신청 인증서 종류")
    @Column(name = "application_certification")
    var applicationCertification: String? = null

    fun toEntity(applicationRequest: ApplicationRequest): Application {
        this.applicationCategory = applicationRequest.applicationCategory
        this.applicationStatus = applicationRequest.applicationStatus
        this.applicationCertification = applicationRequest.applicationCertification
        return this
    }
}
