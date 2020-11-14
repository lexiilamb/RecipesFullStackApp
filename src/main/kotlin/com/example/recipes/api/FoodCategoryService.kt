package com.example.recipes.api

import com.example.recipes.models.FoodCategoryEntity
import com.example.recipes.repositories.FoodCategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FoodCategoryService(
    private val foodCategoryRepo: FoodCategoryRepository
) {

    fun getAllFoodCategories(): List<FoodCategoryEntity> = foodCategoryRepo.findAll()

    fun saveFoodCategory(category: FoodCategoryEntity): FoodCategoryEntity = foodCategoryRepo.save(category)

    fun getFoodCategoryById(foodCategoryId: Long): Optional<FoodCategoryEntity> =  foodCategoryRepo.findById(foodCategoryId)

    fun deleteFoodCategoryById(foodCategoryId: Long): Optional<Unit> {
        return foodCategoryRepo.findById(foodCategoryId).map { foodCategory ->
            foodCategoryRepo.delete(foodCategory)
        }
    }

    fun deleteAllFoodCategories() {
        foodCategoryRepo.deleteAll()
    }

    fun updateFoodCategoryById(foodCategoryId: Long, newCategory: FoodCategoryEntity): Optional<FoodCategoryEntity> {
        return foodCategoryRepo.findById(foodCategoryId).map { existingCategory ->
            val updatedCategory: FoodCategoryEntity = existingCategory.copy(
                category = if (newCategory.category == "") existingCategory.category else newCategory.category)
            foodCategoryRepo.save(updatedCategory)
        }
    }
}