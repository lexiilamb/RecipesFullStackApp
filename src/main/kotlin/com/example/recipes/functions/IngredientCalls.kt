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

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = IngredientEntity(ingredient_id = rs.getInt("appliance_id"),
                name = rs.getString("name"),
                food_group = rs.getString("name")
            )

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Steak'", "'Meat'")
        insertRow(connection, "'Potatoes'", "'Vegetables'")
        insertRow(connection, "'Chicken'", "'Meat'")
        insertRow(connection, "'Pineapple'", "'Vegetables'")
        insertRow(connection, "'Carrots'", "'Meat'")
        insertRow(connection, "'Peanuts'", "'Legumes '")
        insertRow(connection, "'Condensed Milk'", "'Dairy'")

    }

    fun insertRow(connection: Connection,
                  name: String,
                  foodGroup: String?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (name, food_group) values ($name, $foodGroup);"
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