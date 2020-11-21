package com.example.recipes.models

import javax.persistence.*

@Entity
@Table(name = "appliances")
data class ApplianceEntity(

    val name: String = "",
    val recipe_id: Int? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val appliance_id: Int? = null
)