package com.example.recipes.functions

import com.example.recipes.models.RecipeEntity
import java.sql.Connection

class RecipeCalls {
    fun queryRecipes(SCHEMA: String, connection: Connection): List<RecipeEntity> {
        var resultList = ArrayList<RecipeEntity>()

        val sql = "SELECT * FROM $SCHEMA.recipes"
        val rs = connection.createStatement().executeQuery(sql)

//        resultList.add(listOf("TABLE RECIPES:"))
        while (rs.next()) {
           var recipe = RecipeEntity( recipe_id = rs.getInt("recipe_id"),
               title = rs.getString("title"),
               description = rs.getString("description"),
               prep_time = rs.getInt("prep_time"),
               cook_time = rs.getInt("cook_time"),
               servings = rs.getInt("servings"))

            resultList.add(recipe)
        }

        return resultList
    }

    fun insertRecipes(table: String, connection: Connection) {
        insertRow(table, connection, "'Good Pho'", "'chicken'", 10, 40, 4)
        insertRow(table, connection, "'Good Pie'", "'pumpkin'", 5, 60, 1)
        insertRow(table, connection, "'mac&cheese'", "'super cheesy'", 5, 10, 8)
        insertRow(table, connection, "'Perogies'", "'with potatoes'", 5, 15, 1)
        insertRow(table, connection, "'THIS'", "'IS STUPID'", 5, 15, 1)
    }

    fun insertRow(table: String, connection: Connection,
                  title: String,
                  description: String,
                  prep_time: Int,
                  cook_time: Int,
                  servings: Int) {

        connection.setAutoCommit(false);
        val sql = "insert into $table (title, description, prep_time, cook_time, servings) values ($title, $description, $prep_time, $cook_time, $servings);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

}