package com.example.recipes.api

import com.example.recipes.functions.AppFunctions
import com.example.recipes.functions.Queries
import com.example.recipes.models.RecipeEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class AppController{

   val app = AppFunctions()
   val queries = Queries()

    @GetMapping
    fun allTables() {
        app.createTables()
    }

     @GetMapping("/recipes")
     fun getAllRecipes(): List<RecipeEntity> {
      return queries.getAllRecipes()
     }

     @PostMapping("/recipes")
     fun addRecipe(@ModelAttribute  newRecipeEntity: RecipeEntity): List<RecipeEntity> {
        println(newRecipeEntity)
        queries.saveRecipe(newRecipeEntity)
         return queries.getAllRecipes()
     }

     @DeleteMapping("/recipes/{id}")
     fun deleteRecipe(@PathVariable id: Int): List<RecipeEntity> {
      queries.deleteRecipe(id)
      return queries.getAllRecipes()
     }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}