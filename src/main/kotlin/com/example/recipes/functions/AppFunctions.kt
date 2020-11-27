package com.example.recipes.functions

import com.example.recipes.models.RecipeEntity
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
		category varchar(255),
		description varchar(255),
		prep_time integer,
		cook_time integer,
		servings integer,
		last_made date,
		primary key (recipe_id)
	)
	""")

    private val categoriesTable = listOf("categories",
        """create table categories (
		category_id int not null auto_increment,
		name varchar(255) not null,
		description varchar(255) not null,
		primary key (category_id)
	)""")

    private val foodGroupsTable = listOf("food_groups",
        """create table food_groups (
		food_group_id int not null auto_increment,
		name varchar(255) not null,
		description varchar(255) not null,
		primary key (food_group_id)
	)""")

    private val ingredientsTable = listOf("ingredients",
        """create table ingredients (
		ingredient_id int not null auto_increment,
		name varchar(255) not null,
		food_group varchar(255) not null,
		primary key (ingredient_id)
	)""")

    private val ingredientsListsTable = listOf("ingredients_lists",
        """create table ingredients_lists (
		ingredients_list_id int not null auto_increment,
		recipe_id int not null,
		ingredient varchar(255) not null,
		description varchar(255) not null,
		measurement_type varchar(255) not null,
		measurement_amount numeric(4,2) not null,
		foreign key (recipe_id) references recipes(recipe_id),
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

    private val equipmentTable = listOf("equipment",
        """create table equipment (
		equipment_id int not null auto_increment,
		name varchar(225) not null,
		description varchar(225) not null,
		primary key (equipment_id)
	)""")

    private val recipeEquipmentTable = listOf("recipe_equipment",
        """create table recipe_equipment (
		recipe_equipment_id int not null auto_increment,
		name varchar(225) not null,
		recipe_id int not null,
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (recipe_equipment_id)
	)""")

    private val tables = listOf(
        recipesTable,
        categoriesTable,
        foodGroupsTable,
        ingredientsTable,
        ingredientsListsTable,
        instructionsTable,
        equipmentTable
    )

    private val recipeCalls = RecipeCalls()
    private val categoryCalls = CategoryCalls()
    private val foodGroupCalls = FoodGroupCalls()
    private val ingredientCalls = IngredientCalls()
    private val ingredientsListCalls = IngredientsListCalls()
    private val instructionCalls = InstructionCalls()
    private val equipmentCalls = EquipmentCalls()

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
        recipeCalls.insertTableData(connection)
        categoryCalls.insertTableData(connection)
        foodGroupCalls.insertTableData(connection)
        ingredientCalls.insertTableData(connection)
        ingredientsListCalls.insertTableData(connection)
        instructionCalls.insertTableData(connection)
        equipmentCalls.insertTableData(connection)
    }

    private fun prepareTable(connection: Connection) {
        val metaData = connection.metaData
        tables.forEach { table ->
            val rs = metaData.getTables(null, SCHEMA, table[0], null)

            if (!rs.next()) {
                createTable(connection, table[1])

                if (table[0] == "recipes") {
                    val sql = "CREATE INDEX recipe_title ON recipes (title);"
                    createTable(connection, sql)
                }
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