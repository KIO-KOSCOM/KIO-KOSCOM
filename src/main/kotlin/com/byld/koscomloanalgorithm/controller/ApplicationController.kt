package com.byld.koscomloanalgorithm.controller

import com.byld.koscomloanalgorithm.domain.request.ApplicationRequest
import com.byld.koscomloanalgorithm.domain.response.ApplicationResponse
import com.byld.koscomloanalgorithm.domain.service.ApplicationService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "대출 신청")
@RestController
@RequestMapping("/v1/application")
class ApplicationController(
    private val applicationService: ApplicationService,
) {
    @PostMapping
    fun createApplication(
        @RequestBody applicationRequest: ApplicationRequest,
    ): ResponseEntity<ApplicationResponse> {
        val applicationResponse = applicationService.inquireApplication(applicationRequest)
        return applicationResponse.let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable applicationId: Long,
    ): ResponseEntity<ApplicationResponse> {
        val applicationResponse = applicationService.getApplication(applicationId)
        return applicationResponse.let { ResponseEntity.ok(it) }
    }
}
