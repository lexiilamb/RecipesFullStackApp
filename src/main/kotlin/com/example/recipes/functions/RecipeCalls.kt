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

    fun queryTable(query: String, SCHEMA: String, connection: Connection): List<RecipeEntity> {
        var resultList = ArrayList<RecipeEntity>()

        val rs = connection.createStatement().executeQuery(query)

        while (rs.next()) {
           var tuple = RecipeEntity( recipe_id = rs.getInt("recipe_id"),
               title = rs.getString("title"),
               category = rs.getString("category"),
               description = rs.getString("description"),
               prep_time = rs.getInt("prep_time"),
               cook_time = rs.getInt("cook_time"),
               servings = rs.getInt("servings"))

            resultList.add(tuple)
        }

        return resultList
    }

    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Garlic Butter Steak Bites'", "'Beef'", "'Made with garlic butter sauce'", 5, 10, 4)
        insertRow(connection, "'Good Pie'", "'Baking'",  "'pumpkin'", 5, 60, 1)
        insertRow(connection, "'mac&cheese'", "'Vegetarian'",  "'super cheesy'", 5, 10, 8)
        insertRow(connection, "'Perogies'", "'Vegetarian'",  "'with potatoes'", 5, 15, 1)
    }

    fun insertRow(connection: Connection,
                  title: String,
                  category: String?,
                  description: String?,
                  prep_time: Int?,
                  cook_time: Int?,
                  servings: Int?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (title, category, description, prep_time, cook_time, servings) values ($title, $category, $description, $prep_time, $cook_time, $servings);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<RecipeEntity> {
        val properties = Properties()
        val query = "SELECT * FROM $SCHEMA.$tableName"

        //Populate the properties file with user name and password
        with(properties) {
            put("user", "root")
            put("password", "root")
        }

        //Open a connection to the database
        DriverManager
            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
            .use { connection ->
                return queryTable(query, SCHEMA, connection)
            }
    }

    fun save(newRecipe: RecipeEntity) {
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
                    "'${newRecipe.category}'",
                    "'${newRecipe.description}'",
                    newRecipe.prep_time,
                    newRecipe.cook_time,
                    newRecipe.servings)
            }
    }

    fun deleteDependencies(id: Int, tableName: String, connection: Connection) {
        connection.setAutoCommit(false);
        val sql = "delete from $tableName where $tupleId = $id;"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun delete(id: Int) {
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

//                deleteDependencies(id, "categories", connection)
                deleteDependencies(id, "ingredients_lists", connection)
                deleteDependencies(id, "appliances", connection)
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