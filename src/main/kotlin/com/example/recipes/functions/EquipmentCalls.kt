package com.example.recipes.functions

import com.example.recipes.models.EquipmentEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class EquipmentCalls {
    val SCHEMA = "recipesdb"
    val tableName = "equipment"
    val tupleId = "equipment_id"

    fun queryTable(SCHEMA: String, connection: Connection): List<EquipmentEntity> {
        var resultList = ArrayList<EquipmentEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName ORDER BY name ASC"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = EquipmentEntity(equipment_id = rs.getInt("equipment_id"),
                name = rs.getString("name"),
                description = rs.getString("description"),
                recipe_id = rs.getInt("recipe_id"))

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Oven'", "'or toaster oven'", 1)
        insertRow(connection, "'Toaster Oven'", "'or oven'", 1)
        insertRow(connection, "'Mixing Bowls'", "'-'", 1)
        insertRow(connection, "'Mixer'", "'-'", 1)
        insertRow(connection, "'Muffin Pan'", "'-'", 1)
        insertRow(connection, "'Frying Pan'", "'-'", 2)
        insertRow(connection, "'Oven'", "'-'", 3)
        insertRow(connection, "'Skillet'", "'Ovenproof'", 3)
        insertRow(connection, "'Oven'", "'-'", 4)
        insertRow(connection, "'Mixing Bowls'", "'-'", 4)
        insertRow(connection, "'Loaf Pan'", "'-'", 4)
        insertRow(connection, "'Mixing Bowls'", "'-'", 5)
        insertRow(connection, "'Frying Pan'", "'-'", 5)
        insertRow(connection, "'Baking Sheet'", "'-'", 5)
        insertRow(connection, "'Oven'", "'-'", 5)

    }

    fun insertRow(connection: Connection,
                  name: String,
                  description: String,
                  recipe_id: Int) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (name, description, recipe_id) values ($name, $description, $recipe_id);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<EquipmentEntity> {
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

    fun save(newTuple: EquipmentEntity) {
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
                    newTuple.description,
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