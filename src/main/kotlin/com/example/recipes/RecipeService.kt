package com.example.recipes

import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.Id

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

    fun updateRecipeById(recipeId: Long, newRecipe: RecipeEntity): Optional<RecipeEntity> {
        return recipeRepo.findById(recipeId).map { existingRecipe ->
            val updatedRecipe: RecipeEntity = existingRecipe.copy(
                title = if (newRecipe.title == "") existingRecipe.title else newRecipe.title,
                description = if (newRecipe.description == "") existingRecipe.description else newRecipe.description,
                prepTimeMinutes = if (newRecipe.prepTimeMinutes == null) existingRecipe.prepTimeMinutes else newRecipe.prepTimeMinutes,
                cookTimeMinutes = if (newRecipe.cookTimeMinutes == null) existingRecipe.cookTimeMinutes else newRecipe.cookTimeMinutes,
                readyInMinutes = if (newRecipe.readyInMinutes == null) existingRecipe.readyInMinutes else newRecipe.readyInMinutes,
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