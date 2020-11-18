package com.example.recipes.api

import com.example.recipes.models.RecipeEntity
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class RecipeService(
) {

    fun printTables(listOfLists: ArrayList<List<String>>) {

    }

//    fun getAllRecipes(): List<RecipeEntity> = recipeRepo.findAll()

//    fun saveRecipe(recipe: RecipeEntity): RecipeEntity = recipeRepo.save(recipe)
//
//    fun getRecipeById(recipeId: Long): Optional<RecipeEntity> =  recipeRepo.findById(recipeId)
//
//    fun deleteRecipeById(recipeId: Long): Optional<Unit> {
//        return recipeRepo.findById(recipeId).map { recipe ->
//            recipeRepo.delete(recipe)
//        }
//    }
//
//    fun deleteAllRecipes() {
//        recipeRepo.deleteAll()
//    }
//
//    fun updateRecipeById(recipeId: Long, newRecipe: RecipeEntity): Optional<RecipeEntity> {
//        return recipeRepo.findById(recipeId).map { existingRecipe ->
//            val updatedRecipe: RecipeEntity = existingRecipe.copy(
//                title = if (newRecipe.title == "") existingRecipe.title else newRecipe.title,
//                description = if (newRecipe.description == "") existingRecipe.description else newRecipe.description,
//                prep_time = if (newRecipe.prep_time == null) existingRecipe.prep_time else newRecipe.prep_time,
//                cook_time = if (newRecipe.cook_time == null) existingRecipe.cook_time else newRecipe.cook_time,
//                servings = if (newRecipe.servings == null) existingRecipe.servings else newRecipe.servings)
//            recipeRepo.save(updatedRecipe)
//        }
//    }
}