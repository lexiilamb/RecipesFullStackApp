package com.example.recipes.api

import com.example.recipes.functions.EquipmentCalls
import com.example.recipes.models.EquipmentEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/appliances")
class ApplianceController{

    val calls = EquipmentCalls()

    @GetMapping()
    fun getAll(): List<EquipmentEntity> {
        return calls.getAll()
    }

    @PostMapping()
    fun add(@RequestBody newEntity: EquipmentEntity): List<EquipmentEntity> {
        calls.save(newEntity)
        return calls.getAll()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): List<EquipmentEntity> {
        calls.delete(id)
        return calls.getAll()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}