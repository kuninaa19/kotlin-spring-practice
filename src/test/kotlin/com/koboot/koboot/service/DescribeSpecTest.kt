package com.koboot.koboot.service

import com.koboot.koboot.classes.Calculator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DescribeSpecTest : DescribeSpec({
    describe("계산기 - 덧셈") {
        val calculator  = Calculator()
        context("a와 b 설정하기") {
            val a = 20
            val b = 10
            it("30을 반환한다") {
                val expected = 30
                val result = calculator.sum(a,b)

                result shouldBe  expected
            }
        }
    }
})