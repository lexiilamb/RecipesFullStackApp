package com.example.recipes

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class RecipesApplication

fun main(args: Array<String>) {
	SpringApplication.run(RecipesApplication::class.java, *args)
}