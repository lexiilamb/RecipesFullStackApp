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
        return categoryCalls.getAllCategories()
    }

    @PostMapping()
    fun addCategory(@RequestBody newCategoryEntity: FoodCategoryEntity): List<FoodCategoryEntity> {
        categoryCalls.saveCategory(newCategoryEntity)
        return categoryCalls.getAllCategories()
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Int): List<FoodCategoryEntity> {
        categoryCalls.deleteCategory(id)
        return categoryCalls.getAllCategories()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}