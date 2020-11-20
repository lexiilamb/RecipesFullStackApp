package com.example.recipes.functions

import com.example.recipes.models.FoodCategoryEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class FoodCategoryCalls {
    val SCHEMA = "recipesdb"
    val tableName = "food_categories"
    val tupleId = "food_category_id"

    fun queryCategories(SCHEMA: String, connection: Connection): List<FoodCategoryEntity> {
        var resultList = ArrayList<FoodCategoryEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = FoodCategoryEntity(food_category_id = rs.getInt("food_category_id"),
                category = rs.getString("category"),
                recipe_id = rs.getInt("recipe_id"))

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertCategories(connection: Connection) {
        insertRow(connection, "'Baking'", 1)
        insertRow(connection, "'Beef'", 1)
        insertRow(connection, "'Breakfast'", 1)
        insertRow(connection, "'Chicken'", 1)
        insertRow(connection, "'Soup'", 1)
        insertRow(connection, "'Dessert'", 1)
        insertRow(connection, "'Muffins'", 1)
        insertRow(connection, "'Pork'", 1)
    }

    fun insertRow(connection: Connection,
                  category: String,
                  recipeId: Int?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (category, recipe_id) values ($category, $recipeId);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAllCategories(): List<FoodCategoryEntity> {
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
                return queryCategories(SCHEMA, connection)
            }
    }

    fun saveCategory(newCategory: FoodCategoryEntity) {
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
                    "'${newCategory.category}'",
                    newCategory.recipe_id)
            }
    }

    fun deleteCategory(id: Int) {
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
                val sql = "delete from $tableName where $tupleId = $id;"
                with(connection) {
                    createStatement().execute(sql)
                    connection.commit()
                }
            }
    }
}