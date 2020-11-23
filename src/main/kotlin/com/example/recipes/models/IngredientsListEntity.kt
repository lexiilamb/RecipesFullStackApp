package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "ingredients_lists")
data class IngredientsListEntity(

    val recipe_id: Int,
    val ingredient: String,
    val description: String,
    val measurement_type: String,
    val measurement_amount: Double,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val ingredients_list_id: Int? = null
)