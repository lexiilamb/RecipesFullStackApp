package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "recipes")
data class RecipeEntity(

	val title: String = "",
	val description: String? = "",
	val prep_time_minutes: Int? = null,
	val cook_time_minutes: Int? = null,
	val servings: Int? = null,
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val recipe_id: Long? = null
)