package com.example.users.api

import com.example.users.models.UserEntity
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@RestController
@RequestMapping("/users")
class UserController{
    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun homepage(): String {
        return "Welcome to DB Project"
    }

    @GetMapping("/all")
    fun getAll(): List<UserEntity> {
        return userService.getAll()
    }

    @PostMapping("/save")
    fun createNew(@ModelAttribute user: UserEntity): UserEntity = userService.save(user)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): Optional<UserEntity> = userService.getById(id)

    @DeleteMapping("/delete")
    fun deleteUser(@RequestParam( "id") id: Long): ResponseEntity<Void> {
        return userService.deleteById(id).map { _  ->
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/update/{id}")
    fun updateUser(@PathVariable id: Long, @ModelAttribute newUser: UserEntity): ResponseEntity<UserEntity>  {
        val updatedUser: Optional<UserEntity> = userService.update(id, newUser)
        return updatedUser.map { updated ->
            ResponseEntity.ok().body(updated)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/deleteAll")
    fun deleteAll() = userService.deleteAll();


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}