package com.example.recipes.functions

import com.example.recipes.models.CategoryEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class CategoryCalls {
    private val SCHEMA = "recipesdb"
    private val tableName = "categories"
    private val tupleId = "category_id"

    private fun queryTable(SCHEMA: String, connection: Connection): List<CategoryEntity> {
        val resultList = ArrayList<CategoryEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName ORDER BY name ASC"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            val tuple = CategoryEntity(category_id = rs.getInt("category_id"),
                name = rs.getString("name"),
                description = rs.getString("description"))

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Soup'", "'Ramen, pho'")
        insertRow(connection, "'Baking'", "'Cakes, pastries recipes'")
        insertRow(connection, "'Beef'", "'Ground beef and steak recipes'")
        insertRow(connection, "'Vegetarian'", "''")
        insertRow(connection, "'Breakfast'", "''")
        insertRow(connection, "'Muffins'", "''")
        insertRow(connection, "'Pork'", "''")
        insertRow(connection, "'Chicken'", "'Legs, wings, thighs, and breast recieps'")
    }

    private fun insertRow(connection: Connection,
                          name: String,
                          description: String?) {

        connection.setAutoCommit(false)
        val sql = "insert into $tableName (name, description) values ($name, $description);"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    fun getAll(): List<CategoryEntity> {
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

    fun save(newCategory: CategoryEntity) {
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
                    "'${newCategory.name}'",
                    "'${newCategory.description}'")
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
                connection.setAutoCommit(false)
                val sql = "delete from $tableName where $tupleId = $id;"
                with(connection) {
                    createStatement().execute(sql)
                    connection.commit()
                }
            }
    }
}