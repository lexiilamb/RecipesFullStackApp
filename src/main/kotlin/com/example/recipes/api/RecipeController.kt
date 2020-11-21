package com.example.recipes.api

import com.example.recipes.functions.RecipeCalls
import com.example.recipes.models.RecipeEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
class RecipeController{

    val recipeCalls = RecipeCalls()

    @GetMapping()
    fun getAll(): List<RecipeEntity> {
        return recipeCalls.getAll()
    }

    @PostMapping()
    fun addRecipe(@RequestBody newRecipeEntity: RecipeEntity): List<RecipeEntity> {
        recipeCalls.save(newRecipeEntity)
        return recipeCalls.getAll()
    }

    @DeleteMapping("/{id}")
    fun deleteRecipe(@PathVariable id: Int): List<RecipeEntity> {
        recipeCalls.delete(id)
        return recipeCalls.getAll()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}