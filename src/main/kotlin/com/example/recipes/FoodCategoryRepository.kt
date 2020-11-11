package com.example.recipes

import org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodCategoryRepository : JpaRepository<FoodCategoryEntity, Long> {
}
