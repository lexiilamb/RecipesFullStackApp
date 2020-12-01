package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "equipment")
data class EquipmentEntity(

    val name: String = "",
    val description: String = "",
    val recipe_id: Int,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val equipment_id: Int? = null
)