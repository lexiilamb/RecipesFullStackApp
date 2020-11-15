package com.example.recipes

import com.example.recipes.models.FoodCategoryCalls
import com.example.recipes.models.RecipeCalls
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

private const val SCHEMA = "recipesdb"
private val recipesTable = listOf("recipes",
	"""
		create table recipes (
		recipe_id int not null auto_increment,
		title varchar(255) not null,
		description varchar(255),
		prep_time_minutes integer,
		cook_time_minutes integer,
		servings integer,
		last_made date,
		create index title on recipes(title),
		primary key (recipe_id)
	)
	""")

private val foodCategoriesTable = listOf("food_categories",
	"""create table food_categories (
		food_category_id int not null auto_increment,
		category varchar(45) not null,
		recipe_id int not null,
		create index category on food_categories(category),
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (food_category_id)
	)""")

private val ingredientsTable = listOf("ingredients",
	"""create table ingredients (
		ingredient_id int not null auto_increment,
		name varchar(45) not null,
		measurement_type varchar(45) not null,
		measurement_amount int not null,
		recipe_id int not null,
		create index ingredient_name on ingredients(name),
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (ingredient_id)
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
		create index appliance_name on appliances(name),
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (appliance_id)
	)""")

private val utensilsTable = listOf("utensils",
	"""create table utensils (
		utensils_id int not null auto_increment,
		name varchar(225) not null,
		recipe_id int not null,
		create index utensil_name on appliances(name),
		foreign key (recipe_id) references recipes(recipe_id),
		primary key (utensils_id)
	)""")

private val tables = listOf(
	recipesTable,
	foodCategoriesTable,
	ingredientsTable,
	instructionsTable,
	appliancesTable,
	utensilsTable
)

val recipeCalls = RecipeCalls()
val categoryCalls = FoodCategoryCalls()

fun main(args: Array<String>) {
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
			prepareTable(connection)
			insertData(connection)
			query(connection)
		}
}

private fun insertData(connection: Connection) {
	tables.forEach { table ->
		when (table[0]) {
			"recipes" -> recipeCalls.insertRecipes(table[0], connection)
			"food_categories" -> categoryCalls.insertCategories(table[0], connection)
		}
	}

//	recipeCalls.insertRecipes(tables[0][0], connection)
}

private fun query(connection: Connection) {
	tables.forEach { table ->
		when (table[0]) {
			"recipes" -> recipeCalls.queryRecipes(SCHEMA, table[0], connection)
			"food_categories" -> categoryCalls.queryCategories(SCHEMA, table[0], connection)
		}
	}
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
	//SQL statement to create a table
	val sql = tableQuery.trimMargin()

	with(connection) {
		//Get and instance of statement from the connection and use
		//the execute() method to execute the sql
		createStatement().execute(sql)

		//Commit the change to the database
		commit()
	}
}