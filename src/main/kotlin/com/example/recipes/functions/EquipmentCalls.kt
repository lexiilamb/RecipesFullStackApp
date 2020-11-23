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

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = EquipmentEntity(equipment_id = rs.getInt("equipment_id"),
                name = rs.getString("name"),
                description = rs.getString("description"))

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Oven'", "'-'")
        insertRow(connection, "'Stove'", "'-'")
        insertRow(connection, "'Toaster Oven'", "'-'")
        insertRow(connection, "'Microwave'", "'-'")
        insertRow(connection, "'Blender'", "'-'")
        insertRow(connection, "'Mixer'", "'-'")
        insertRow(connection, "'Frying Pan'", "'-'")
        insertRow(connection, "'Baking Pan'", "'-'")

    }

    fun insertRow(connection: Connection,
                  name: String,
                  description: String) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (name, description) values ($name, $description);"
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
                    newTuple.description)
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