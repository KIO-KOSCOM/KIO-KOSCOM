package com.byld.koscomloanalgorithm.domain.repository

import com.byld.koscomloanalgorithm.domain.entity.MortgageUserInput
import org.springframework.data.jpa.repository.JpaRepository

interface MortgageUserInputRepository : JpaRepository<MortgageUserInput, Long>
