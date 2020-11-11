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