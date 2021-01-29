package com.example.users

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BoilerPlateApplication

fun main(args: Array<String>) {
	SpringApplication.run(BoilerPlateApplication::class.java, *args)
}