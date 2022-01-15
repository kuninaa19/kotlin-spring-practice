package com.koboot.koboot.services

import com.koboot.koboot.dto.UserJoinCompanyDTO
import com.koboot.koboot.dto.UserReqDTO
import com.koboot.koboot.dto.UserResDTO
import com.koboot.koboot.entity.User
import com.koboot.koboot.repository.CompanyRepository
import com.koboot.koboot.repository.UserRepository
import org.apache.http.HttpStatus
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.http.HttpResponse
import javax.transaction.Transactional
import kotlin.streams.toList

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val companyRepository: CompanyRepository,
    @Autowired private val modelMapper: ModelMapper,
) {
    fun getUsers(): List<UserResDTO> {
        val users = userRepository.findAll()
        if (users.isEmpty()) {
            log.error("전체유저가 조회가 안됩니다/")
            throw Exception("에러요")
        }
        val result = users.stream().map { modelMapper.map(it, UserResDTO::class.java) }.toList()
        log.info(result.toString())
        return result
    }

    fun userById(id: Long): UserResDTO {
        val anUser = userRepository.findById(id).orElse(null)
        log.info("유저 조회합니다. $anUser")
        if (anUser != null) {
            val result = modelMapper.map(anUser, UserResDTO::class.java)
            log.info("유저 정보 전달합니다.$result")
            return result
        } else {
            throw Exception("에러발생")
        }
    }

    @Transactional
    fun joinACompany(userJoinCompanyDto: UserJoinCompanyDTO): UserResDTO {
        try {
            val aCompany = companyRepository.findById(userJoinCompanyDto.companyId).orElse(null)
            val anUser = userRepository.findById(userJoinCompanyDto.userId).orElse(null)

            anUser.company = aCompany
            aCompany.addUser(anUser)

            val result = UserResDTO.ModelMapper.entityToDto(anUser)
            log.info("DTO 변환 유저정보 $result")

            return result

        } catch (e: Exception) {
            log.error("회사 조회 혹은 유저 조회 실패")
            throw Exception("에러발생")
        }
    }

    fun createUser(userReqDTO: UserReqDTO): UserResDTO {
        val aCompany = companyRepository.findById(userReqDTO.companyId).orElse(null) ?: throw Exception("조회가 안되네요")
        val anUser = modelMapper.map(userReqDTO, User::class.java)
        aCompany.addUser(anUser)
        companyRepository.save(aCompany)

        val result = UserResDTO.ModelMapper.entityToDto(anUser)
        log.info("DTO 변환 우저정보 $result")

        return result
    }

    @Transactional
    fun leaveACompany(id: Long): UserResDTO {
        val anUser = userRepository.findById(id).orElse(null) ?: throw Exception("찾을 수 없는 유저")

//        아래와 동일하다
//        anUser.updateCompany(anUser.company!!)
        anUser.leaveCompany()

        val result = UserResDTO.ModelMapper.entityToDto(anUser)
        log.info("DTO 변환 우저정보 $result")

        return result

    }

    companion object {
        private val log: Logger = LogManager.getLogger(this.javaClass.name)
    }
}