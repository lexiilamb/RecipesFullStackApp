package com.example.recipes.functions

import java.sql.Connection

class FoodCategoryCalls {

    fun queryCategories(SCHEMA: String, table: String, connection: Connection): ArrayList<List<String>> {
        var resultList = ArrayList<List<String>>()
        var item = emptyList<String>()

        val sql = "SELECT * FROM $SCHEMA.${table}"
        val rs = connection.createStatement().executeQuery(sql)

        resultList.add(listOf("TABLE CATEGORIES:"))
        while (rs.next()) {
            item = listOf("Id: ${rs.getInt("food_category_id")}",
            "Category: ${rs.getString("category")}",
            "Recipe Id: ${rs.getInt("recipe_id")}")

            resultList.add(item)
        }

        return resultList
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