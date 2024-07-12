package com.byld.koscomloanalgorithm.domain.repository

import com.byld.koscomloanalgorithm.domain.entity.ApplicationUserInput
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationUserInputRepository : JpaRepository<ApplicationUserInput, Long>
