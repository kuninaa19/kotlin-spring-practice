package com.koboot.koboot.service

import com.koboot.koboot.classes.UserData
import com.koboot.koboot.dto.UserReqDTO
import com.koboot.koboot.dto.UserResDTO
import com.koboot.koboot.entity.User
import com.koboot.koboot.repository.UserRepository
import com.koboot.koboot.services.UserService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class ActivateMockkTest() : BehaviorSpec({
    val userRepo = mockk<UserRepository>()
    val userService = mockk<UserService>()

    Given("새로운 객체를 생성하자") {
        val id: Long = 1
        val userMock = UserReqDTO(name = "선기철", email = "rlcjfdl0052@dmail.com", age = 27, companyId = 1)
        println(userMock.toString())
        When("이메일을 사용한 데이터 조회") {
            every { userRepo.findById(1).orElse(null) } returns null
            every { userService.createUser(userMock) } answers {
                UserResDTO(id = 1, name = "선기철", email = "rlcjfdl0052@dmail.com", age = 27, companyName = "Tesla")
            }

            val user = userRepo.findById(id).orElse(null)
            if (user == null) {
                println(user.toString())

                val result = userService.createUser(userMock)

                val expected = UserData(id = 1, name = "선기철", email = "rlcjfdl0052@dmail.com", age = 27)
                then("만약존재한다면 가져온 값과 비교한다") {
//                    verify(exactly = 1) { userRepo.save(userMock) }

                    result shouldBe expected
                }
            }
        }
    }


    xGiven("동일한 이메일을 가진 유저가 있는지 확인하자") {
        val email = "rlcjfdl0052@dmail.com"
        val userMock = UserData(name = "선기철", email = "rlcjfdl0052@dmail.com", age = 27)
        println(userMock.toString())
        When("이메일을 사용한 데이터 조회") {
            every { userRepo.findByEmail(email) } answers {
                Optional.of(User())
//                name="배기영","qorldud053@dmail.com","29"
            }
            val user = userRepo.findByEmail(email).orElse(null)
            println(user.toString())

            val expect = null
            then("만약존재한다면 가져온 값과 비교한다") {
                verify(exactly = 1) { userRepo.findByEmail(email) }

                user shouldBe expect
            }
        }
    }
})