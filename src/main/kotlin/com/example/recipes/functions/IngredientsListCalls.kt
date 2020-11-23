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
        insertRow(connection, 2, "'Beef'", "'Ground'", "'Pound'", 1.25)
        insertRow(connection, 2, "'Egg'", "'-'", "'-'", 1.0)
        insertRow(connection, 2, "'Onion'", "'Chopped'", "'-'", 1.0)
        insertRow(connection, 2, "'Milk'", "'-'", "'Cup'", 1.0)
        insertRow(connection, 2, "'Bread Crumbs'", "'Dried'", "'Cup'", 1.0)
        insertRow(connection, 2, "'Brown Sugar'", "'-'", "'Tablespoon'", 2.0)
        insertRow(connection, 2, "'Mustard'", "'Prepared Mustard'", "'Tablespoon'", 2.0)
        insertRow(connection, 2, "'Ketchup'", "'-'", "'Cup'", 0.33)
        insertRow(connection, 3, "'Bananas'", "'Ripened'", "'Large'", 3.0)
        insertRow(connection, 3, "'White Sugar'", "'Ripened'", "'Cup'", 0.5)
        insertRow(connection, 3, "'Egg'", "'-'", "'-'", 1.0)
        insertRow(connection, 3, "'Baking Soda'", "'-'", "'Teaspoon'", 1.0)
        insertRow(connection, 3, "'Baking Powder'", "'-'", "'Teaspoon'", 1.0)
        insertRow(connection, 3, "'Flour'", "'All-Purpose'", "'Cup'", 1.5)
        insertRow(connection, 3, "'Butter'", "'Melted'", "'Cup'", 0.33)
        insertRow(connection, 3, "'Nuts'", "'Optional'", "'-'", 0.0)
        insertRow(connection, 3, "'Chocolate Chips'", "'Optional'", "'-'", 0.0)
        insertRow(connection, 4, "'Chicken'", "'Breast'", "'Pound'", 1.0)
        insertRow(connection, 4, "'Onion'", "'Sliced'", "'Cup'", 0.5)
        insertRow(connection, 4, "'Apples'", "'Red'", "'Cup'", 0.5)
        insertRow(connection, 4, "'Syrup'", "'Maple-flavored'", "'Cup'", 0.5)
        insertRow(connection, 4, "'Italian Dressing'", "'-'", "'Cup'", 0.33)
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