package com.example.recipes.models

import java.sql.Connection

class FoodCategoryCalls {
    fun queryCategories(SCHEMA: String, table: String, connection: Connection) {
        val sql = "SELECT * FROM $SCHEMA.${table}"
        val rs = connection.createStatement().executeQuery(sql)
        while (rs.next()) {
            println("Id: ${rs.getInt("food_category_id")}\t\t" +
                "Category: ${rs.getString("category")}\t\t" +
                "Recipe Id: ${rs.getInt("recipe_id")}")
        }
    }
    
    fun insertCategories(table: String, connection: Connection) {
        insertRow(table, connection, "'Soup'", 1)
        insertRow(table, connection, "'Noodles'", 1)
        insertRow(table, connection, "'Meat'", 1)
        insertRow(table, connection, "'Dessert'", 2)
        insertRow(table, connection, "'Vegetarian'", 3)
        insertRow(table, connection, "'Side'", 3)
    }

    fun insertRow(table: String, connection: Connection,
                  category: String,
                  recipeId: Int) {

        connection.setAutoCommit(false);
        val sql = "insert into $table (category, recipe_id) values ($category, $recipeId);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

}