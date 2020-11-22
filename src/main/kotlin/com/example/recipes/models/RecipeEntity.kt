package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "recipes")
data class RecipeEntity(

    var title: String = "default",
    var category: String? = "default",
    var description: String? = "default",
    var prep_time: Int? = null,
    var cook_time: Int? = null,
    var servings: Int? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var recipe_id: Int? = null
)