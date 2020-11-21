package com.example.recipes.api

import com.example.recipes.functions.FoodCategoryCalls
import com.example.recipes.models.FoodCategoryEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class FoodCategoryController{

    val categoryCalls = FoodCategoryCalls()

    @GetMapping()
    fun getAllCategories(): List<FoodCategoryEntity> {
        return categoryCalls.getAll()
    }

    @PostMapping()
    fun addCategory(@RequestBody newCategoryEntity: FoodCategoryEntity): List<FoodCategoryEntity> {
        categoryCalls.save(newCategoryEntity)
        return categoryCalls.getAll()
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Int): List<FoodCategoryEntity> {
        categoryCalls.delete(id)
        return categoryCalls.getAll()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}