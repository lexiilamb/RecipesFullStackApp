package com.example.recipes.functions

import com.example.recipes.models.ApplianceEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class ApplianceCalls {
    val SCHEMA = "recipesdb"
    val tableName = "appliances"
    val tupleId = "appliance_id"

    fun queryTable(SCHEMA: String, connection: Connection): List<ApplianceEntity> {
        var resultList = ArrayList<ApplianceEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = ApplianceEntity(appliance_id = rs.getInt("appliance_id"),
                name = rs.getString("name"),
                recipe_id = rs.getInt("recipe_id"))

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Oven'", 1)
        insertRow(connection, "'Stove'", 1)
        insertRow(connection, "'Toaster Oven'", 1)
        insertRow(connection, "'Microwave'", 1)
        insertRow(connection, "'Blender'", 1)
        insertRow(connection, "'Mixer'", 1)

    }

    fun insertRow(connection: Connection,
                  name: String,
                  recipeId: Int?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (name, recipe_id) values ($name, $recipeId);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<ApplianceEntity> {
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

    fun save(newTuple: ApplianceEntity) {
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
                    newTuple.recipe_id)
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