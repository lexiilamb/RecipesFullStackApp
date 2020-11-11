package com.example.recipes

import javax.persistence.*

@Entity
@Table(name = "foodcategories")
data class FoodCategoryEntity(

	val category: String,
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?
)