package com.byld.koscomloanalgorithm.domain.entity

import com.byld.koscomloanalgorithm.domain.enum.LoanPurpose
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
@Table(name = "product")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    var id: Long? = null

    @CreationTimestamp
    var insertTime: LocalDateTime? = null

    @UpdateTimestamp
    var updateTime: LocalDateTime? = null

    @Comment("BankId")
    @Column(name = "bank_id")
    var bankId: Long? = null

    @Comment("상품 이름")
    @Column(name = "product_name")
    var productName: String? = null

    @Comment("상품 종류")
    @Column(name = "product_category")
    @Enumerated(EnumType.STRING)
    var productCategory: LoanPurpose? = null

    @Comment("호출 가능 여부")
    @Column(name = "call_enable")
    var callEnable: Boolean? = null
}
