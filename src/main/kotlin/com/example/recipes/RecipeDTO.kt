package com.example.recipes

import java.io.Serializable


class RecipeDTO(
    val title: String,
    val description: String,
    val preTimeMinutes: Int?,
    val cookTimeMinutes: Int?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val id: Long
) : Serializable