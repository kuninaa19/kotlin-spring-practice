package com.koboot.koboot.controllers

import com.koboot.koboot.dto.UserReqDTO
import com.koboot.koboot.dto.UserResDTO
import com.koboot.koboot.services.UserService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController(@Autowired private val userService: UserService) {
//    @GetMapping
//    fun getUsers(): ResponseEntity<>{
//        return ResponseEntity(userService.,HttpStatus.OK)
//    }

    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: Long
    ): ResponseEntity<UserResDTO> {
        log.info("로깅테스트")
        return ResponseEntity(userService.userById(id), HttpStatus.OK)
    }

    @PostMapping()
    fun createUser(
        @RequestBody userDto: UserReqDTO,
    ): ResponseEntity<UserResDTO> {
        return ResponseEntity(userService.createUser(userDto), HttpStatus.OK)
    }
    companion object {
        private val log: Logger = LogManager.getLogger(this.javaClass.name)
    }
}