package com.example.recipes.api

import com.example.recipes.models.RecipeEntity
import com.example.recipes.repositories.FoodCategoryRepository
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@RestController
@RequestMapping("/recipes")
class RecipeController{
    @Autowired
    lateinit var recipeService: RecipeService

    @Autowired
    lateinit var foodCategoryRepo: FoodCategoryRepository

    @GetMapping
    fun homepage(): String {
        return "Welcome to DB Project"
    }

    @GetMapping("/all")
    fun getAllRecipes(): List<RecipeEntity> {
        return recipeService.getAllRecipes()
    }

    @PostMapping("/save")
    fun createNewRecipe(@ModelAttribute recipe: RecipeEntity): RecipeEntity = recipeService.saveRecipe(recipe)

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Long): Optional<RecipeEntity> = recipeService.getRecipeById(recipeId)

    @DeleteMapping("/delete")
    fun deleteRecipe(@RequestParam( "id") recipeId: Long): ResponseEntity<Void> {
        return recipeService.deleteRecipeById(recipeId).map { _  ->
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/update/{recipeId}")
    fun updateRecipe(@PathVariable recipeId: Long,
            @ModelAttribute newRecipe: RecipeEntity): ResponseEntity<RecipeEntity>  {
        val updatedRecipe: Optional<RecipeEntity> = recipeService.updateRecipeById(recipeId, newRecipe)
        return updatedRecipe.map { updated ->
            ResponseEntity.ok().body(updated)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/recipes/deleteAll")
    fun deleteAllRecipes() = recipeService.deleteAllRciepes();


//    @GetMapping("/categories")
//    fun getAllCategories(): List<FoodCategoryEntity> {
//    return foodCategoryRepo.findAll()
//    }
//
//
//
//    @GetMapping("/allIngredients")
//    fun getAllIngredients(): Any {
//        println("allIngredients")
//        return "Yay Ingredients"
//    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}