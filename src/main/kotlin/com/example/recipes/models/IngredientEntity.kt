package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "ingredients")
data class IngredientEntity(

    val name: String = "",
    val food_group: String? = "",
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val ingredient_id: Int? = null
)