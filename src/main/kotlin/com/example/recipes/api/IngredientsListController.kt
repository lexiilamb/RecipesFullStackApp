package com.example.recipes.api

import com.example.recipes.functions.IngredientsListCalls
import com.example.recipes.models.IngredientsListEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ingredients-lists")
class IngredientsListController{

    val ingredientsListCalls = IngredientsListCalls()

    @GetMapping()
    fun getAllLists(): List<IngredientsListEntity> {
        return ingredientsListCalls.getAll()
    }

//    @PostMapping()
//    fun addCategory(@RequestBody newIngredientListEntity: IngredientListEntity): List<IngredientListEntity> {
//        ingredientsListCalls.save(newIngredientListEntity)
//        return ingredientsListCalls.getAll()
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteCategory(@PathVariable id: Int): List<IngredientListEntity> {
//        ingredientsListCalls.delete(id)
//        return ingredientsListCalls.getAll()
//    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}