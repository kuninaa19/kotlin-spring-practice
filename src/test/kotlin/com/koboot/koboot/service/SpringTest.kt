package com.koboot.koboot.service

import com.koboot.koboot.dto.UserResDTO
import com.koboot.koboot.services.UserService
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.spring.SpringListener
import io.mockk.every
import io.mockk.mockk
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringTest : BehaviorSpec() {
    override fun listeners(): List<TestListener> {
        return listOf(SpringListener)
    }

    @Autowired
    private val userService: UserService? = null

    val target = mockk<UserService>()

    init {
        Given("유저번호로 DB에서 유저 조회시도") {
            val userId: Long = 1
            val result = userService?.userById(userId)
            When("예상되는 결과값 설정 - 비교") {
                every { target.userById(1) } answers {
                    UserResDTO(id = 1, name = "윤동주", email = "하늘과바람과별과시", age = 41, companyName = "TESLA")
                }
                val expected = target.userById(1)
                result?.name shouldBe expected.name
                // Then 설정시 NoSuchMethodError 발생
            }
        }
    }
}