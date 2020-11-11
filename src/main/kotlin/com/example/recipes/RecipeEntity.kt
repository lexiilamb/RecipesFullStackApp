package com.example.recipes

import javax.persistence.*

@Entity
@Table(name = "recipes")
data class RecipeEntity(

	val title: String = "",
	val description: String? = "",
	val prepTimeMinutes: Int? = null,
	val cookTimeMinutes: Int? = null,
	val readyInMinutes: Int? = null,
	val servings: Int? = null,
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = 0
)