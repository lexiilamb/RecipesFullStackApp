package com.example.recipes.api

import com.example.recipes.models.FoodCategoryEntity
import com.example.recipes.repositories.FoodCategoryRepository
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@RestController
@RequestMapping("/categories")
class FoodCategoryController{
    @Autowired
    lateinit var foodCategoryService: FoodCategoryService

    @Autowired
    lateinit var foodCategoryRepo: FoodCategoryRepository

    @GetMapping
    fun homepage(): String {
        return "Welcome to DB Project"
    }

    @GetMapping("/all")
    fun getAllRecipes(): List<FoodCategoryEntity> {
        return foodCategoryService.getAllFoodCategories()
    }

    @PostMapping("/save")
    fun createNewRecipe(@ModelAttribute recipe: FoodCategoryEntity): FoodCategoryEntity = foodCategoryService.saveFoodCategory(recipe)

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Long): Optional<FoodCategoryEntity> = foodCategoryService.getFoodCategoryById(recipeId)

    @DeleteMapping("/delete")
    fun deleteRecipe(@RequestParam( "id") recipeId: Long): ResponseEntity<Void> {
        return foodCategoryService.deleteFoodCategoryById(recipeId).map { _  ->
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/update/{recipeId}")
    fun updateRecipe(@PathVariable recipeId: Long,
            @ModelAttribute newRecipe: FoodCategoryEntity): ResponseEntity<FoodCategoryEntity>  {
        val updatedRecipe: Optional<FoodCategoryEntity> = foodCategoryService.updateFoodCategoryById(recipeId, newRecipe)
        return updatedRecipe.map { updated ->
            ResponseEntity.ok().body(updated)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/recipes/deleteAll")
    fun deleteAllRecipes() = foodCategoryService.deleteAllFoodCategories();


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}