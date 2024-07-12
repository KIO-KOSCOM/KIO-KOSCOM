package com.byld.koscomloanalgorithm.domain.repository

import com.byld.koscomloanalgorithm.domain.entity.Application
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationRepository : JpaRepository<Application, Long>
