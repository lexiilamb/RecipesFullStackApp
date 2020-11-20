package com.example.recipes.functions

import com.example.recipes.models.RecipeEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class RecipeCalls {
    val SCHEMA = "recipesdb"
    val tableName = "recipes"
    val tupleId = "recipe_id"

    fun queryRecipes(SCHEMA: String, connection: Connection): List<RecipeEntity> {
        var resultList = ArrayList<RecipeEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
           var tuple = RecipeEntity( recipe_id = rs.getInt("recipe_id"),
               title = rs.getString("title"),
               description = rs.getString("description"),
               prep_time = rs.getInt("prep_time"),
               cook_time = rs.getInt("cook_time"),
               servings = rs.getInt("servings"))

            resultList.add(tuple)
        }

        return resultList
    }

    fun insertRecipes(connection: Connection) {
        insertRow(connection, "'Garlic Butter Steak Bites'", "'Made with garlic butter sauce'", 5, 10, 4)
        insertRow(connection, "'Good Pie'", "'pumpkin'", 5, 60, 1)
        insertRow(connection, "'mac&cheese'", "'super cheesy'", 5, 10, 8)
        insertRow(connection, "'Perogies'", "'with potatoes'", 5, 15, 1)
        insertRow(connection, "'THIS'", "'IS STUPID'", 5, 15, 1)
    }

    fun insertRow(connection: Connection,
                  title: String,
                  description: String,
                  prep_time: Int?,
                  cook_time: Int?,
                  servings: Int?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (title, description, prep_time, cook_time, servings) values ($title, $description, $prep_time, $cook_time, $servings);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

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
                return queryRecipes(SCHEMA, connection)
            }
    }

    fun saveRecipe(newRecipe: RecipeEntity) {
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
                insertRow(connection,
                    "'${newRecipe.title}'",
                    "'${newRecipe.description}'",
                    newRecipe.prep_time,
                    newRecipe.cook_time,
                    newRecipe.servings)
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
                val sql = "delete from food_categories where $tupleId = $id;"
                with(connection) {
                    createStatement().execute(sql)
                    connection.commit()
                }
            }

        //Open a connection to the database
        DriverManager
            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
            .use { connection ->
                connection.setAutoCommit(false);
                val sql = "delete from $tableName where $tupleId = $id;"
                with(connection) {
                    createStatement().execute(sql)
                    connection.commit()
                }
            }
    }
}