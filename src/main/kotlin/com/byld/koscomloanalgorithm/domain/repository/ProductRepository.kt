package com.byld.koscomloanalgorithm.domain.repository

import com.byld.koscomloanalgorithm.domain.entity.Product
import com.byld.koscomloanalgorithm.domain.enum.LoanPurpose
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findProductById(id: Long): Product?

    fun findByProductCategoryIsAndCallEnableIsTrue(productCategory: LoanPurpose?): List<Product>
}
