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
                  recipeId: Int) {

        connection.setAutoCommit(false);
        val sql = "insert into food_categories (category, recipe_id) values ($category, $recipeId);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

}