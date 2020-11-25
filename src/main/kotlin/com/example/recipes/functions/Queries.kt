package com.example.recipes.functions

import com.example.recipes.models.IngredientsListEntity
import com.example.recipes.models.InstructionEntity
import com.example.recipes.models.RecipeEntity
import java.sql.Connection
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

    fun ingredients(recipeTitle: String): List<IngredientsListEntity> {
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
                return ingredientsQuery(recipeTitle, connection)
            }
    }

    fun instructions(recipeTitle: String): List<InstructionEntity> {
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
                return instructionsQuery(recipeTitle, connection)
            }
    }

    fun ingredientsQuery(recipeTitle: String, connection: Connection): List<IngredientsListEntity> {
        var resultList = ArrayList<IngredientsListEntity>()

        val sql = "SELECT * FROM recipesdb.ingredients_lists WHERE recipe_id = (SELECT recipe_id FROM recipesdb.recipes WHERE title = '${recipeTitle}')"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = IngredientsListEntity(
                ingredients_list_id = rs.getInt("ingredients_list_id"),
                recipe_id = rs.getInt("recipe_id"),
                ingredient = rs.getString("ingredient"),
                description = rs.getString("description"),
                measurement_type = rs.getString("measurement_type"),
                measurement_amount = rs.getDouble("measurement_amount")
            )

            resultList.add(tuple)
        }

        return resultList
    }

    fun instructionsQuery(recipeTitle: String, connection: Connection): List<InstructionEntity> {
        var resultList = ArrayList<InstructionEntity>()

        val sql = "SELECT * FROM recipesdb.instructions WHERE recipe_id = (SELECT recipe_id FROM recipesdb.recipes WHERE title = '${recipeTitle}')"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = InstructionEntity(
                instruction_id = rs.getInt("instruction_id"),
                step = rs.getInt("step"),
                instruction = rs.getString("instruction"),
                recipe_id = rs.getInt("recipe_id")
            )

            resultList.add(tuple)
        }

        return resultList
    }
}