package com.example.recipes.functions

import com.example.recipes.models.IngredientsListEntity
import com.example.recipes.models.RecipeEntity
import java.sql.DriverManager
import java.util.*

class Queries {
    val app = AppFunctions()
    val recipeCalls = RecipeCalls()
    val ingredientsListCalls = IngredientsListCalls()


    fun recipes(query: String): List<RecipeEntity> {
        val properties = Properties()

        //Populate the properties file with user name and password
        with(properties) {
            put("user", "root")
            put("password", "root")
        }

        //Open a connection to the database
        DriverManager
            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
            .use { connection ->
                return recipeCalls.queryTable(query, connection)
            }
    }

    fun ingredients(query: String): List<IngredientsListEntity> {
        val properties = Properties()

        //Populate the properties file with user name and password
        with(properties) {
            put("user", "root")
            put("password", "root")
        }

        //Open a connection to the database
        DriverManager
            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
            .use { connection ->
                return ingredientsListCalls.queryTable(query, connection)
            }
    }
}