package com.koboot.koboot.dto

data class UserReqDTO(
    val name: String = "",
    val email: String = "",
    val age: Int = 0
)

data class UserResDTO(
    val id: Long? = null,
    val name: String = "",
    val email: String = "",
    val age: Int = 0
)