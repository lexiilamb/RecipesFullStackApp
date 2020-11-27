package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "ingredients_lists")
data class IngredientsListEntity(

    val ingredient: String,
    val recipe_title: String? = "",
    val recipe_id: Int,
    val description: String,
    val measurement_type: String,
    val measurement_amount: Double,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val ingredients_list_id: Int? = null
)