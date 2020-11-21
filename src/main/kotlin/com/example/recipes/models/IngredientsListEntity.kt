package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "ingredients_lists")
data class IngredientsListEntity(

    val ingredient: String,
    val recipe_id: Int,
    val measurement_type: String,
    val measurement_amount: Int,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val ingredients_list_id: Int? = null
)