package com.example.recipes.functions

import com.example.recipes.models.IngredientEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class IngredientCalls {
    val SCHEMA = "recipesdb"
    val tableName = "ingredients"
    val tupleId = "ingredient_id"

    fun queryTable(SCHEMA: String, connection: Connection): List<IngredientEntity> {
        var resultList = ArrayList<IngredientEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName ORDER BY name ASC"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = IngredientEntity(ingredient_id = rs.getInt("ingredient_id"),
                name = rs.getString("name"),
                food_group = rs.getString("food_group")
            )

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Steak'", "'Protein'")
        insertRow(connection, "'Olvie Oil'", "'Oils and Fats'")
        insertRow(connection, "'Butter'", "'Oils and Fats'")
        insertRow(connection, "'Garlic'", "'Vegetables'")
        insertRow(connection, "'Parsley'", "'Vegetables'")
        insertRow(connection, "'Ground Beef'", "'Protein'")
        insertRow(connection, "'Egg'", "'Protein'")
        insertRow(connection, "'Onion'", "'Vegetables'")
        insertRow(connection, "'Milk'", "'Dairy'")
        insertRow(connection, "'Bread Crumbs'", "'Grains'")
        insertRow(connection, "'Sugar (Brown)'", "'-'")
        insertRow(connection, "'Mustard'", "'-'")
        insertRow(connection, "'Ketchup'", "'-'")
        insertRow(connection, "'Bananas'", "'Fruits'")
        insertRow(connection, "'Sugar (White)'", "'-'")
        insertRow(connection, "'Baking Soda'", "'-'")
        insertRow(connection, "'Baking Powder'", "'-'")
        insertRow(connection, "'Flour'", "'-'")
        insertRow(connection, "'Chicken'", "'Protein'")
        insertRow(connection, "'Apple'", "'Fruit'")
        insertRow(connection, "'Syrup'", "'-'")
        insertRow(connection, "'Italian Dressing'", "'-'")
        insertRow(connection, "'Soy Sauce'", "'-'")
        insertRow(connection, "'Honey'", "'-'")
        insertRow(connection, "'Ginger'", "'-'")

    }

    fun insertRow(connection: Connection,
                  name: String,
                  food_group: String?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (name, food_group) values ($name, $food_group);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<IngredientEntity> {
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
                return queryTable(SCHEMA, connection)
            }
    }

    fun save(newTuple: IngredientEntity) {
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
                    "'${newTuple.name}'",
                    newTuple.food_group)
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
                connection.setAutoCommit(false);
                val sql = "delete from $tableName where $tupleId = $id;"
                with(connection) {
                    createStatement().execute(sql)
                    connection.commit()
                }
            }
    }
}