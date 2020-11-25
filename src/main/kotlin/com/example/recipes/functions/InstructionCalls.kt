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
        insertRow(connection, 1, 1, "'Heat the olive oil in a large pan over high heat. Season the steak with salt and pepper to taste.'")
        insertRow(connection, 1, 2, "'Place the steak in the pan in a single layer; you may have to work in batches depending on the size of your pan. Cook for 3-4 minutes, stirring occasionally, until golden brown. Repeat with remaining meat if needed.'")
        insertRow(connection, 1, 3, "'Add the butter and garlic to the pan; cook for 1-2 minutes, stirring to coat the meat in the sauce.'")
        insertRow(connection, 1, 4, "'Sprinkle with parsley and serve.'")
        insertRow(connection, 2, 1, "'Preheat oven to 350 degrees F (175 degrees C).'")
        insertRow(connection, 2, 2, "'In a large bowl, combine the beef, egg, onion, milk and bread OR cracker crumbs.'")
        insertRow(connection, 2, 3, "'Season with salt and pepper to taste and place in a lightly greased 9x5-inch loaf pan, or form into a loaf and place in a lightly greased 9x13-inch baking dish.'")
        insertRow(connection, 2, 4, "'In a separate small bowl, combine the brown sugar, mustard and ketchup. Mix well and pour over the meatloaf.'")
        insertRow(connection, 2, 5, "'Bake at 350 degrees F (175 degrees C) for 1 hour.'")
        insertRow(connection, 3, 1, "'Mash bananas, add sugar and the slightly beaten egg.'")
        insertRow(connection, 3, 2, "'Stir in melted butter.'")
        insertRow(connection, 3, 3, "'Stir in dry ingredients.'")
        insertRow(connection, 3, 4, "'Spoon into medium size muffin pan (I use the paper muffin cups inside pan).'")
        insertRow(connection, 3, 5, "'Bake 20 minutes at 350F degrees - 375F degrees.'")
        insertRow(connection, 4, 1, "'Heat oven to 350°F.'")
        insertRow(connection, 4, 2, "'Cook chicken in large ovenproof skillet sprayed with cooking spray on medium-high heat 3 min. on each side or until evenly browned. Remove from heat.'")
        insertRow(connection, 4, 3, "'Top chicken with onions; surround with apples. Mix syrup and dressing; pour over chicken.'")
        insertRow(connection, 4, 4, "'Bake 20 to 25 min. or until chicken is done (165ºF). Drizzle with syrup mixture from bottom of skillet.'")
        insertRow(connection, 5, 1, "'Whisk crushed pineapple, soy suace, honey, ginger and garlic in a bowl. Pour half in plastic bag and resure the other half.'")
        insertRow(connection, 5, 2, "'Place chicken breasts in resealale bag. Massage/coat chicken with marinade. Refrigerate for 30 to overnight.'")
        insertRow(connection, 5, 3, "'Place the marinated chicken breasts on a foil-lined baking sheet, spoon some of the chunky pineapple/ginger, and broil for 8 to 10 minutes on each side, for a total of 16-20 minutes.'")
        insertRow(connection, 5, 4, "'Heat the reserved teriyaki sauce in a saucepan. Bring to a simmer and cook for 3 minutes or until thickened slightly.'")
        insertRow(connection, 5, 5, "'When chicken is done, Slice the chicken, pour the teriyaki sauce over top and serve immediately.'")

    }

    fun insertRow(connection: Connection,
                  recipe_id: Int,
                  step: Int,
                  instruction: String) {

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
                    newTuple.recipe_id,
                    newTuple.step,
                    "'${newTuple.instruction}'")
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