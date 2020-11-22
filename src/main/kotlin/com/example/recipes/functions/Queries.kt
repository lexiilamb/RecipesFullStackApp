package com.example.recipes.functions

class Queries {
    val app = AppFunctions()
    val recipeCalls = RecipeCalls()
    val categoryCalls = CategoryCalls()


//    fun getAllRecipes(): List<RecipeEntity> {
//        val properties = Properties()
//
//        //Populate the properties file with user name and password
//        with(properties) {
//            put("user", "root")
//            put("password", "root")
//        }
//
//        //Open a connection to the database
//        DriverManager
//            .getConnection("jdbc:mysql://localhost:3306/recipesdb", properties)
//            .use { connection ->
//                return recipeCalls.queryRecipes(app.SCHEMA, connection)
//            }
//    }
}