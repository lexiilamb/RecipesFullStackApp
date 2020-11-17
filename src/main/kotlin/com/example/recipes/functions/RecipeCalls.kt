package com.example.recipes.functions

import java.sql.Connection

class RecipeCalls {
    fun queryRecipes(SCHEMA: String, table: String, connection: Connection): ArrayList<List<String>> {
        var resultList = ArrayList<List<String>>()
        var item = emptyList<String>()

        val sql = "SELECT * FROM $SCHEMA.${table}"
        val rs = connection.createStatement().executeQuery(sql)

        resultList.add(listOf("TABLE RECIPES:"))
        while (rs.next()) {
            item = listOf("Id: ${rs.getInt("recipe_id")}",
            "Title: ${rs.getString("title")}",
            "Description: ${rs.getString("description")}",
            "Prep Time: ${rs.getInt("prep_time_minutes")}",
            "Cook Time: ${rs.getInt("cook_time_minutes")}",
            "Servings: ${rs.getInt("servings")}")

            resultList.add(item)
        }

        return resultList
    }

    fun insertRecipes(table: String, connection: Connection) {
        insertRow(table, connection, "'Good Pho'", "'chicken'", 10, 40, 4)
        insertRow(table, connection, "'Good Pie'", "'pumpkin'", 5, 60, 1)
        insertRow(table, connection, "'mac&cheese'", "'super cheesy'", 5, 10, 8)
    }

    fun insertRow(table: String, connection: Connection,
                  title: String,
                  description: String,
                  prep_time_minutes: Int,
                  cook_time_mintues: Int,
                  servings: Int) {

        connection.setAutoCommit(false);
        val sql = "insert into $table (title, description, prep_time_minutes, cook_time_minutes, servings) values ($title, $description, $prep_time_minutes, $cook_time_mintues, $servings);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

}