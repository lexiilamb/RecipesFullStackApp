package com.example.recipes

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RecipesApplicationTests {

	@Autowired
	lateinit var recipService: RecipeService

	@Test
	fun contextLoads() {
	}

//	@Test
//	fun `gets all recipes`() {
//		val expectedRecipes = listOf(
//				title = "omlet",
//				description = "yummy",
//				preTimeMinutes = 1,
//				cookTimeMinutes = 1,
//				readyInMinutes = 2,
//				servings = 1,
//				id = 1
//			))
//
//		val recipes = RecipeService.
//	}

}
