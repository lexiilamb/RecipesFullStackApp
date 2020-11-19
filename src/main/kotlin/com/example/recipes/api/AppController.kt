package com.example.recipes.api

import com.example.recipes.functions.AppFunctions
import com.example.recipes.functions.Queries
import com.example.recipes.models.RecipeEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/")
class AppController{

   val app = AppFunctions()
   val queries = Queries()

//    fun blog(model: Model): ArrayList<List<String>> {
//     model["title"] = "Blog"
//     model["cat"] = "Words"
//     return app.displayTables()
//    }

    @GetMapping
    fun allTables() {
        app.createTables()
    }

     @GetMapping("/recipes")
     fun getAllRecipes(): List<RecipeEntity> {
//      app.createTables()
      return queries.getAllRecipes()
     }

     @GetMapping("/addRecipe")
     fun addRecipe() {
      queries.addRecipe()
     }

      @GetMapping("/deleteRecipe")
      fun deleteRecipe() {
       queries.deleteRecipe(6)
      }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}