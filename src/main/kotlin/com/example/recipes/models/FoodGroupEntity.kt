package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "food_groups")
data class FoodGroupEntity(

    val name: String = "",
    val description: String? = "",
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val food_group_id: Int? = null
)