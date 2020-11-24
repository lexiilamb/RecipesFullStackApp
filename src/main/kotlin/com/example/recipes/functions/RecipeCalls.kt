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

    fun queryTable(query: String, connection: Connection): List<RecipeEntity> {
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
        insertRow(connection, "'Meatloaf'", "'Beef'",  "'Quick to make'", 10, 70, 8)
        insertRow(connection, "'Banana Muffins'", "'Muffins'",  "'With or without nuts'", 10, 20, 12)
        insertRow(connection, "'Maple-Baked Apple Chicken'", "'Chicken'",  "'Sweet and savory'", 15, 25, 4)
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
        val query = "SELECT * FROM $SCHEMA.$tableName ORDER BY title ASC"

        //Populate the properties file with user name and password
        with(properties) {
            put("user", "root")
            put("password", "root")
        }

        //Open a connection to the database
        DriverManager
            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
            .use { connection ->
                return queryTable(query, connection)
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
                deleteDependencies(id, "instructions", connection)
//                deleteDependencies(id, "equipment", connection)
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