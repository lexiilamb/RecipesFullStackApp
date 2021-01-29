package com.example.users.models

import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(

	val name: String = "",
	val number: Double? = null,
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)