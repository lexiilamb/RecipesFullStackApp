package com.example.recipes.functions

import com.example.recipes.models.InstructionEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class InstructionCalls {
    val SCHEMA = "recipesdb"
    val tableName = "instructions"
    val tupleId = "instruction_id"

    fun queryTable(SCHEMA: String, connection: Connection): List<InstructionEntity> {
        var resultList = ArrayList<InstructionEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = InstructionEntity(
                instruction_id = rs.getInt("instruction_id"),
                step = rs.getInt("step"),
                instruction = rs.getString("instruction"),
                recipe_id = rs.getInt("recipe_id")
            )

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, 1, "'Heat the olive oil in a large pan over high heat. Season the steak with salt and pepper to taste.'", 1)
        insertRow(connection, 2, "'Place the steak in the pan in a single layer; you may have to work in batches depending on the size of your pan. Cook for 3-4 minutes, stirring occasionally, until golden brown. Repeat with remaining meat if needed.'", 1)
        insertRow(connection, 3, "'Add the butter and garlic to the pan; cook for 1-2 minutes, stirring to coat the meat in the sauce.'", 1)
        insertRow(connection, 4, "'Sprinkle with parsley and serve.'", 1)
        insertRow(connection, 1, "'blank'", 1)

    }

    fun insertRow(connection: Connection,
                  step: Int,
                  instruction: String,
                  recipe_id: Int) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (step, instruction, recipe_id) values ($step, $instruction, $recipe_id);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<InstructionEntity> {
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

    fun save(newTuple: InstructionEntity) {
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
                    newTuple.step,
                    "'${newTuple.instruction}'",
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