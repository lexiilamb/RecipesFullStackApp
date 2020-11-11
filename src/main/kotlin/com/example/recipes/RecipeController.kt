package com.example.recipes

import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@RestController
@RequestMapping("/api")
class RecipeController{
    @Autowired
    lateinit var recipeService: RecipeService

    @Autowired
    lateinit var foodCategoryRepo: FoodCategoryRepository

    @GetMapping
    fun homepage(): String {
        return "Welcome to DB Project"
    }

    @GetMapping("/recipes")
    fun getAllRecipes(): List<RecipeEntity> {
        println("FUCK THIS")
        return recipeService.getAllRecipes()
    }

    @PostMapping("/recipes/save")
    fun createNewRecipe(@ModelAttribute recipe: RecipeEntity): RecipeEntity = recipeService.saveRecipe(recipe)

    @GetMapping("/recipes/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Long): Optional<RecipeEntity> = recipeService.getRecipeById(recipeId)


    @GetMapping("/categories")
    fun getAllCategories(): List<FoodCategoryEntity> {
    return foodCategoryRepo.findAll()
    }



//    @PostMapping("/addRecipe")
//    fun createRecipe(@Valid @RequestBody recipe : RecipeEntity) : RecipeEntity = recipeRepo.save(recipe)

//    @DeleteMapping("/recipes/deleteAll")
//    fun deleteAllRecipes(): Unit =
//        recipeService.deleteAllRciepes();

    @GetMapping("/allIngredients")
    fun getAllIngredients(): Any {
        println("allIngredients")
        return "Yay Ingredients"
    }



    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message


    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}