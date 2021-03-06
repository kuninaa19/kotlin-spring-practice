package com.koboot.koboot.controllers

import com.koboot.koboot.dto.CompanyReqDTO
import com.koboot.koboot.dto.CompanyResDTO
import com.koboot.koboot.intercepter.Permission
import com.koboot.koboot.intercepter.Permission.Role.USER
import com.koboot.koboot.services.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/companies")
class CompanyController(@Autowired private val companyService: CompanyService) {

    /** @desc @requestAttribute 는 특정 KEY를 가진 REQUEST를 조회한 후 값을 가져옴 */
    @Permission(role = USER)
    @GetMapping
    fun getCompanies(
        @RequestAttribute("uid") uid: String
    ): ResponseEntity<List<CompanyResDTO>> {
        return ResponseEntity(companyService.getCompanies(), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun getCompany(
        @PathVariable id: Long
    ): ResponseEntity<CompanyResDTO> {
        return ResponseEntity(companyService.getCompany(id), HttpStatus.OK)
    }

    @PostMapping()
    fun createCompany(
        @RequestBody companyDto: CompanyReqDTO
    ): ResponseEntity<CompanyResDTO> {
        return ResponseEntity(companyService.createCompany(companyDto), HttpStatus.CREATED)
    }
}