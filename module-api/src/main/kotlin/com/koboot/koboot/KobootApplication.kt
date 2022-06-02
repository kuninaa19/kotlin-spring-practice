package com.koboot.koboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KobootApplication

fun main(args: Array<String>) {
	runApplication<KobootApplication>(*args)
}
