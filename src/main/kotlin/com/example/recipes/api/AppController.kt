package com.example.recipes.api

import com.example.recipes.RecipesApplication
import com.example.recipes.functions.AppFunctions
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping
import java.util.*
import kotlin.collections.ArrayList
import org.springframework.ui.Model
import org.springframework.ui.set

@RestController
@RequestMapping("/recipes")
class AppController{

   val app = AppFunctions()

//    fun blog(model: Model): ArrayList<List<String>> {
//     model["title"] = "Blog"
//     model["cat"] = "Words"
//     return app.displayTables()
//    }

    @GetMapping
    fun allTables(): ArrayList<List<String>> {

        return app.displayTables()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}