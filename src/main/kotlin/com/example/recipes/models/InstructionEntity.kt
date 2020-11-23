package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "instructions")
data class InstructionEntity(

    val step: Int,
    val instruction: String,
    val recipe_id: Int,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val instruction_id: Int? = null
)