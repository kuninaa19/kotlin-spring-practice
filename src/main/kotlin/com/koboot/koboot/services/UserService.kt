package com.koboot.koboot.services

import com.koboot.koboot.dto.UserReqDTO
import com.koboot.koboot.dto.UserResDTO
import com.koboot.koboot.entity.User
import com.koboot.koboot.repository.UserRepository
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val modelMapper: ModelMapper,
) {
    fun userById(id: Long): UserResDTO {
        val anUser = userRepository.findById(id).orElse(null)
        log.info("로깅테스트",anUser.toString())
        if (anUser != null) {
            return modelMapper.map(anUser, UserResDTO::class.java)
        } else {
            throw Exception("eeee")
        }
    }

    fun createUser(userReqDTO: UserReqDTO): UserResDTO {
        log.info("로깅테스트 created")
        val anUser = userRepository.save(modelMapper.map(userReqDTO, User::class.java))
        return modelMapper.map(anUser, UserResDTO::class.java)
    }

    companion object {
        private val log: Logger = LogManager.getLogger(this.javaClass.name)
    }
}