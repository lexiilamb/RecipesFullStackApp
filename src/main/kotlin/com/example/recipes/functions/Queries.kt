package com.example.recipes.functions

import com.example.recipes.models.RecipeEntity
import java.sql.DriverManager
import java.util.*

class Queries {
    val app = AppFunctions()
    val recipeCalls = RecipeCalls()
    val categoryCalls = FoodCategoryCalls()


    fun getAllRecipes(): List<RecipeEntity> {
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
                return recipeCalls.queryRecipes(app.SCHEMA, connection)
            }
    }

    fun addRecipe() {
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
                recipeCalls.insertRow(connection, "'New Recipe'", "'New Description'", 1, 1, 1)
            }
    }

    fun deleteRecipe(id: Int) {
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
                connection.setAutoCommit(false);
                val sql = "delete from recipes where recipe_id = $id;"
                with(connection) {
                    createStatement().execute(sql)
                    connection.commit()
                }
            }
    }
}