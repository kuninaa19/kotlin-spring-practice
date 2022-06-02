package com.koboot.koboot.service

import com.koboot.koboot.classes.Calculator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BehaviorSpecTest : BehaviorSpec({
    Given("계산기 - 두값의 곱셈 구하기") {
        val calculator = Calculator()
        When("a와 b가 주어질때") {
            val a = 20
            val b = 10
            Then("두 곱의 결과가 200이다") {
                val expected = 200
                val result = calculator.multiplication(a, b)

                result shouldBe expected
            }
        }
    }
})