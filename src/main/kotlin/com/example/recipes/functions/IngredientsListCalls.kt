package com.example.recipes.functions

import com.example.recipes.models.IngredientsListEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class IngredientsListCalls {
    val SCHEMA = "recipesdb"
    val tableName = "ingredients_lists"
    val tupleId = "ingredients_list_id"

    fun queryTable(SCHEMA: String, connection: Connection): List<IngredientsListEntity> {
        var resultList = ArrayList<IngredientsListEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = IngredientsListEntity(
                ingredients_list_id = rs.getInt("ingredients_list_id"),
                recipe_id = rs.getInt("recipe_id"),
                ingredient = rs.getString("ingredient"),
                description = rs.getString("description"),
                measurement_type = rs.getString("measurement_type"),
                measurement_amount = rs.getDouble("measurement_amount")
            )

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, 1, "'Steak'", "'Sirloin'", "'Pound'", 0.25)
        insertRow(connection, 1, "'Olive Oil'", "'-'","'Tablespoon'", 1.0)
        insertRow(connection, 1, "'Butter'", "'-'", "'Tablespoon'", 2.0)
        insertRow(connection, 1, "'Garlic'", "'Minced'", "'Teaspoon'", 2.0)
        insertRow(connection, 1, "'Parsley'", "'Minced'", "'Tablespoon'", 1.0)
    }

    fun insertRow(connection: Connection,
                  recipe_id: Int,
                  ingredient: String,
                  description: String,
                  measurement_type: String,
                  measurement_amount: Double) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (recipe_id, ingredient, description, measurement_type, measurement_amount) values ($recipe_id, $ingredient, $description, $measurement_type, $measurement_amount);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<IngredientsListEntity> {
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

    fun save(newTuple: IngredientsListEntity) {
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
                    newTuple.recipe_id,
                    "'${newTuple.ingredient}'",
                    "'${newTuple.description}'",
                    "'${newTuple.measurement_type}'",
                    newTuple.measurement_amount)
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