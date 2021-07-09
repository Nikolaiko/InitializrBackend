package com.android.initializr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InitializrApplication

fun main(args: Array<String>) {
	runApplication<InitializrApplication>(*args)
}
