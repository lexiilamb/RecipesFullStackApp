package com.example.recipes.api

import com.example.recipes.functions.CategoryCalls
import com.example.recipes.functions.EquipmentCalls
import com.example.recipes.models.EquipmentEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/equipment")
class EquipmentController{

    val equipmentCalls = EquipmentCalls()

    @GetMapping()
    fun getAllEquipment(): List<EquipmentEntity> {
        return equipmentCalls.getAll()
    }

    @PostMapping()
    fun addEquipment(@RequestBody newEquipmentEntity: EquipmentEntity): List<EquipmentEntity> {
        equipmentCalls.save(newEquipmentEntity)
        return equipmentCalls.getAll()
    }

    @DeleteMapping("/{id}")
    fun deleteEquipment(@PathVariable id: Int): List<EquipmentEntity> {
        equipmentCalls.delete(id)
        return equipmentCalls.getAll()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}