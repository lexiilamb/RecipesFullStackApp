package com.example.recipes.api

import com.example.recipes.functions.AppFunctions
import com.example.recipes.functions.Queries
import com.example.recipes.models.IngredientsListEntity
import com.example.recipes.models.InstructionEntity
import com.example.recipes.models.RecipeEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class AppController{

    val app = AppFunctions()
    val queries = Queries()

    @GetMapping()
    fun allTables(): String {
        app.createTables()
        return "Completed!"
    }

    @PostMapping("/r-query")
    fun queryRecipes(@RequestBody query: String): List<RecipeEntity> {
        return queries.recipes(query)
    }

    @PostMapping("/ingredient-query")
    fun queryIngreientsLists(@RequestBody recipeTitle: String): List<IngredientsListEntity> {
        return queries.ingredients(recipeTitle)
    }

    @PostMapping("/instruction-query")
    fun queryInstructionsLists(@RequestBody recipeTitle: String): List<InstructionEntity> {
        return queries.instructions(recipeTitle)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}