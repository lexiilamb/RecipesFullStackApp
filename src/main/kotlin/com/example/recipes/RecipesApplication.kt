package com.example.recipes

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RecipesApplication

//fun main(args: Array<String>) {
//	runApplication<RecipesApplication>(*args){
//		setBannerMode(Banner.Mode.OFF)
//	}
//}

//https://github.com/Khalidtoak/kotlin-spring-boot-demo-journal-app
//https://github.com/pekka566/spring-boot-kotlin-mysql-rest-api-demo

fun main(args: Array<String>) {
	SpringApplication.run(RecipesApplication::class.java, *args)
}