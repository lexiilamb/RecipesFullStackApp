package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "food_categories")
data class FoodCategoryEntity(

    val category: String = "",
    val recipe_id: Int? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val food_category_id: Int? = null
)