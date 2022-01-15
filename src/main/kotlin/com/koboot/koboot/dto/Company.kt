package com.koboot.koboot.dto

data class CompanyReqDTO(
    val name: String = "",
    val address: String = ""
)

data class CompanyResDTO(
    val id: Long? = null,
    val name: String = "",
    val address: String = ""
)