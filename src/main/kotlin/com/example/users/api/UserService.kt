package com.example.users.api

import com.example.users.models.UserEntity
import com.example.users.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepo: UserRepository
) {

    fun getAll(): List<UserEntity> = userRepo.findAll()

    fun save(user: UserEntity): UserEntity = userRepo.save(user)

    fun getById(userId: Long): Optional<UserEntity> =  userRepo.findById(userId)

    fun deleteById(userId: Long): Optional<Unit> {
        return userRepo.findById(userId).map { user ->
            userRepo.delete(user)
        }
    }

    fun deleteAll() {
        userRepo.deleteAll()
    }

    fun update(userId: Long, newItem: UserEntity): Optional<UserEntity> {
        return userRepo.findById(userId).map { existing ->
            val update: UserEntity = existing.copy(
                name = if (newItem.name == "") existing.name else newItem.name,
                number = if (newItem.number == null) existing.number else newItem.number)
            userRepo.save(update)
        }
    }
}