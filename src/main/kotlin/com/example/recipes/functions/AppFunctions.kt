package com.example.recipes.functions

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class AppFunctions {
    val SCHEMA = "recipesdb"
    private val recipesTable = listOf("recipes",
        """
		create table recipes (
		recipe_id int not null auto_increment,
		title varchar(255) not null,
		description varchar(255),
		prep_time integer,
		cook_time integer,
		servings integer,
		last_made date,
		primary key (recipe_id)
	)
	""")

    private val foodCategoriesTable = listOf("food_categories",
        """create table food_categories (
		food_category_id int not null auto_increment,
		category varchar(255) not null,
		recipe_id int not null,
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (food_category_id)
	)""")

    private val recipeCategoriesTable = listOf("recipe_categories",
        """create table recipe_categories (
		recipe_category_id int not null auto_increment,
		category varchar(255) not null,
		recipe_id int not null,
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (recipe_category_id)
	)""")

    private val ingredientsTable = listOf("ingredients",
        """create table ingredients (
		ingredient_id int not null auto_increment,
		name varchar(255) not null,
		primary key (ingredient_id)
	)""")

    private val ingredientsListsTable = listOf("ingredients_lists",
        """create table ingredients_lists (
		ingredients_list_id int not null auto_increment,
		ingredient_id int not null,
		recipe_id int not null,
		measurement_type varchar(255) not null,
		measurement_amount int not null,
		foreign key (recipe_id) references recipes(recipe_id),
		foreign key (ingredient_id) references ingredients(ingredient_id),
		primary key (ingredients_list_id)
	)""")

    private val instructionsTable = listOf("instructions",
        """create table instructions (
		instruction_id int not null auto_increment,
		step int not null,
		instruction varchar(225) not null,
		recipe_id int not null,
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (instruction_id)
	)""")

    private val appliancesTable = listOf("appliances",
        """create table appliances (
		appliance_id int not null auto_increment,
		name varchar(225) not null,
		recipe_id int not null,
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (appliance_id)
	)""")

    private val utensilsTable = listOf("utensils",
        """create table utensils (
		utensils_id int not null auto_increment,
		name varchar(225) not null,
		recipe_id int not null,
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (utensils_id)
	)""")

    private val tables = listOf(
        recipesTable,
        foodCategoriesTable,
        recipeCategoriesTable,
        ingredientsTable,
        ingredientsListsTable,
        instructionsTable,
        appliancesTable,
        utensilsTable
    )

    val recipeCalls = RecipeCalls()
    val categoryCalls = FoodCategoryCalls()

    fun createTables() {
        val properties = Properties()

        //Populate the properties file with user name and password
        with(properties) {
            put("user", "root")
            put("password", "root")
        }

        //Reset database
        DriverManager
            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
            .use { connection ->
                restartDatabase(connection)
            }

        //Open a connection to the database
        DriverManager
            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
            .use { connection ->
                prepareTable(connection)
                insertData(connection)
            }
    }

    private fun insertData(connection: Connection) {
        recipeCalls.insertRecipes(connection)
        categoryCalls.insertCategories(connection)
    }

    private fun prepareTable(connection: Connection) {
        val metaData = connection.metaData
        tables.forEach { table ->
            val rs = metaData.getTables(null, SCHEMA, table[0], null)

            if (!rs.next()) {
                createTable(connection, table[1])
            } else {
                truncateTable(connection, table[0])
            }
        }
    }

    private fun truncateTable(connection: Connection, tableName: String) {
        connection.setAutoCommit(false);

        var sql = "DELETE FROM $SCHEMA.$tableName"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }

        sql = "ALTER TABLE $SCHEMA.$tableName AUTO_INCREMENT=1"
        with(connection) {
            createStatement().execute(sql)
            connection.commit()
        }
    }

    private fun createTable(connection: Connection, tableQuery: String) {
        connection.setAutoCommit(false);

        //SQL statement to create a table
        val sql = tableQuery.trimMargin()

        with(connection) {
            //Get and instance of statement from the connection and use
            //the execute() method to execute the sql
            createStatement().execute(sql)

            //Commit the change to the database
            connection.commit()
        }
    }

    private fun restartDatabase(connection: Connection) {
        connection.setAutoCommit(false);

        //SQL statement to create a table
        var sql = "DROP SCHEMA $SCHEMA"

        with(connection) {
            //Get and instance of statement from the connection and use
            //the execute() method to execute the sql
            createStatement().execute(sql)

            //Commit the change to the database
            connection.commit()
        }

        sql = "CREATE SCHEMA $SCHEMA"

        with(connection) {
            //Get and instance of statement from the connection and use
            //the execute() method to execute the sql
            createStatement().execute(sql)

            //Commit the change to the database
            connection.commit()
        }
    }
}