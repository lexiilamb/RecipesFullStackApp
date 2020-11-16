package com.example.recipes.functions

import java.sql.Connection

class FoodCategoryCalls {
    fun queryCategories(SCHEMA: String, table: String, connection: Connection): Unit {
        val sql = "SELECT * FROM $SCHEMA.${table}"
        val rs = connection.createStatement().executeQuery(sql)

        println("\n\nCATEGORIES TABLE:")
        while (rs.next()) {
            return println("Id: ${rs.getInt("food_category_id")}\t\t" +
                "Category: ${rs.getString("category")}\t\t\t" +
                "Recipe Id: ${rs.getInt("recipe_id")}")
        }
    }
    
    fun insertCategories(table: String, connection: Connection) {
        insertRow(table, connection, "'Baking'", 1)
        insertRow(table, connection, "'Beef'", 1)
        insertRow(table, connection, "'Breakfast'", 1)
        insertRow(table, connection, "'Chicken'", 1)
        insertRow(table, connection, "'Soup'", 1)
        insertRow(table, connection, "'Dessert'", 1)
        insertRow(table, connection, "'Muffins'", 1)
        insertRow(table, connection, "'Pork'", 1)
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