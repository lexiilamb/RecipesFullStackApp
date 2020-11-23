package com.example.recipes.functions

import com.example.recipes.models.FoodGroupEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class FoodGroupCalls {
    val SCHEMA = "recipesdb"
    val tableName = "food_groups"
    val tupleId = "food_group_id"

    fun queryTable(SCHEMA: String, connection: Connection): List<FoodGroupEntity> {
        var resultList = ArrayList<FoodGroupEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = FoodGroupEntity(food_group_id = rs.getInt("food_group_id"),
                name = rs.getString("name"),
                description = rs.getString("description"))

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Fruits'", "'-'")
        insertRow(connection, "'Vegetables and Legumes'", "'-'")
        insertRow(connection, "'Dairy'", "'-'")
        insertRow(connection, "'Protein'", "'Beef, Chicken, Fish'")
        insertRow(connection, "'Grains'", "'-'")
        insertRow(connection, "'Oils and Fats'", "'-'")
    }

    fun insertRow(connection: Connection,
                  name: String,
                  description: String?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (name, description) values ($name, $description);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<FoodGroupEntity> {
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

    fun save(newTuple: FoodGroupEntity) {
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
                    "'${newTuple.description}'")
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