package com.koboot.koboot.dto

import com.koboot.koboot.entity.User

data class UserReqDTO(
    val name: String = "",
    val email: String = "",
    val age: Int = 0,
    val companyId: Long
)

data class UserResDTO(
    val id: Long? = null,
    val name: String = "",
    val email: String = "",
    val age: Int = 0,
    val companyName: String = ""
) {
    object ModelMapper {
        fun entityToDto(form: User): UserResDTO {
            var companyName = ""
            if (form.company != null) {
                companyName = form.company!!.name
            }

            return UserResDTO(
                id = form.id,
                name = form.name,
                email = form.email,
                age = form.age,
                companyName = companyName
            )
        }
    }
}

data class UserJoinCompanyDTO(
    val userId: Long,
    val companyId: Long
)