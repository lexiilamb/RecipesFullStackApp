package com.example.recipes.functions

import java.sql.Connection

class RecipeCalls {
    fun queryRecipes(SCHEMA: String, table: String, connection: Connection): Unit {
        val sql = "SELECT * FROM $SCHEMA.${table}"
        val rs = connection.createStatement().executeQuery(sql)

        println("\n\nRECIPE TABLE:")
        while (rs.next()) {
            return println("Id: ${rs.getInt("recipe_id")}\t\t" +
                "Title: ${rs.getString("title")}\t\t\t" +
                "Description: ${rs.getString("description")}\t\t" +
                "Prep Time: ${rs.getInt("prep_time_minutes")}\t\t" +
                "Cook Time: ${rs.getInt("cook_time_minutes")}\t\t" +
                "Servings: ${rs.getInt("servings")}")
        }
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