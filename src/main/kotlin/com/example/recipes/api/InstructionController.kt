package com.example.recipes.api

import com.example.recipes.functions.InstructionCalls
import com.example.recipes.models.InstructionEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/instructions")
class InstructionController{

    val instructionEntity = InstructionCalls()

    @GetMapping()
    fun getAllInstructions(): List<InstructionEntity> {
        return instructionEntity.getAll()
    }

//    @PostMapping()
//    fun addCategory(@RequestBody newCategoryEntity: CategoryEntity): List<CategoryEntity> {
//        categoryCalls.save(newCategoryEntity)
//        return categoryCalls.getAll()
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteCategory(@PathVariable id: Int): List<CategoryEntity> {
//        categoryCalls.delete(id)
//        return categoryCalls.getAll()
//    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}