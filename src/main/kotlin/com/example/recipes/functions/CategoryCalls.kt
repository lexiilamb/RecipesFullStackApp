package com.example.recipes.functions

import com.example.recipes.models.CategoryEntity
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList

class CategoryCalls {
    val SCHEMA = "recipesdb"
    val tableName = "categories"
    val tupleId = "category_id"

    fun queryTable(SCHEMA: String, connection: Connection): List<CategoryEntity> {
        var resultList = ArrayList<CategoryEntity>()

        val sql = "SELECT * FROM $SCHEMA.$tableName"
        val rs = connection.createStatement().executeQuery(sql)

        while (rs.next()) {
            var tuple = CategoryEntity(category_id = rs.getInt("category_id"),
                category = rs.getString("category"),
                description = rs.getString("description"))

            resultList.add(tuple)
        }

        return resultList
    }
    
    fun insertTableData(connection: Connection) {
        insertRow(connection, "'Baking'", "'Cakes, pastries recipes'")
        insertRow(connection, "'Beef'", "'Ground beef and steak recipes'")
        insertRow(connection, "'Breakfast'", "''")
        insertRow(connection, "'Chicken'", "'Legs, wings, thighs, and breast recieps'")
        insertRow(connection, "'Soup'", "'Ramen, pho'")
        insertRow(connection, "'Muffins'", "''")
        insertRow(connection, "'Pork'", "''")
        insertRow(connection, "'Vegetarian'", "''")
    }

    fun insertRow(connection: Connection,
                  category: String,
                  description: String?) {

        connection.setAutoCommit(false);
        val sql = "insert into $tableName (category, description) values ($category, $description);"
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
                    "'${newCategory.category}'",
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
                connection.setAutoCommit(false);
                val sql = "delete from $tableName where $tupleId = $id;"
                with(connection) {
                    createStatement().execute(sql)
                    connection.commit()
                }
            }
    }
}