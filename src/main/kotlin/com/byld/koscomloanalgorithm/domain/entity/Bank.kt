package com.byld.koscomloanalgorithm.domain.entity

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

@Entity
@Table(name = "Bank")
class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    var id: Long? = null

    @CreationTimestamp
    var insertTime: LocalDateTime? = null

    @UpdateTimestamp
    var updateTime: LocalDateTime? = null

    @Comment("이름")
    var name: String? = null

    @Comment("금융권 분류")
    @Column(name = "finance_type")
    var financeType: String? = null

    @Comment("회사 로고")
    @Column(name = "company_logo")
    var companyLogo: String? = null
}
