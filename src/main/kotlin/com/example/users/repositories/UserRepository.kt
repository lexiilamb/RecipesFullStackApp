package com.example.users.repositories

import com.example.users.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
}