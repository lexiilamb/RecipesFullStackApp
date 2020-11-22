package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "categories")
data class CategoryEntity(

    val category: String = "",
    val description: String? = "",
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val category_id: Int? = null
)