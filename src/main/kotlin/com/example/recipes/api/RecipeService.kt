package com.example.recipes.api

import com.example.recipes.models.RecipeEntity
import com.example.recipes.repositories.RecipeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecipeService(
    private val recipeRepo: RecipeRepository
) {

    fun getAllRecipes(): List<RecipeEntity> = recipeRepo.findAll()

    fun saveRecipe(recipe: RecipeEntity): RecipeEntity = recipeRepo.save(recipe)

    fun getRecipeById(recipeId: Long): Optional<RecipeEntity> =  recipeRepo.findById(recipeId)

    fun deleteRecipeById(recipeId: Long): Optional<Unit> {
        return recipeRepo.findById(recipeId).map { recipe ->
            recipeRepo.delete(recipe)
        }
    }

    fun deleteAllRciepes() {
        recipeRepo.deleteAll()
    }

    fun updateRecipeById(recipeId: Long, newRecipe: RecipeEntity): Optional<RecipeEntity> {
        return recipeRepo.findById(recipeId).map { existingRecipe ->
            val updatedRecipe: RecipeEntity = existingRecipe.copy(
                title = if (newRecipe.title == "") existingRecipe.title else newRecipe.title,
                description = if (newRecipe.description == "") existingRecipe.description else newRecipe.description,
                prep_time_minutes = if (newRecipe.prep_time_minutes == null) existingRecipe.prep_time_minutes else newRecipe.prep_time_minutes,
                cook_time_minutes = if (newRecipe.cook_time_minutes == null) existingRecipe.cook_time_minutes else newRecipe.cook_time_minutes,
//                readyInMinutes = if (newRecipe.readyInMinutes == null) existingRecipe.readyInMinutes else newRecipe.readyInMinutes,
                servings = if (newRecipe.servings == null) existingRecipe.servings else newRecipe.servings)
            recipeRepo.save(updatedRecipe)
        }
    }



//    private fun entityListToDtoList(entityList: List<RecipeEntity>): List<RecipeDTO> {
//
//        return entityList.map { entityToDTO(it) }
//    }
//
//    private fun entityToDTO(entity: RecipeEntity): RecipeDTO {
//        return RecipeDTO(
//            title = entity.title,
//            description = entity.description,
//            preTimeMinutes = entity.preTimeMinutes,
//            cookTimeMinutes = entity.cookTimeMinutes,
//            readyInMinutes = entity.readyInMinutes,
//            servings = entity.servings,
//            id = entity.id
//        )
//    }
}