package com.example.recipes.repositories

import com.example.recipes.models.FoodCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodCategoryRepository : JpaRepository<FoodCategoryEntity, Long> {
}
