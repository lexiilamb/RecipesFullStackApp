package com.example.recipes.api

import com.example.recipes.functions.ApplianceCalls
import com.example.recipes.functions.FoodCategoryCalls
import com.example.recipes.models.ApplianceEntity
import com.example.recipes.models.FoodCategoryEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/appliances")
class ApplianceController{

    val calls = ApplianceCalls()

    @GetMapping()
    fun getAll(): List<ApplianceEntity> {
        return calls.getAll()
    }

    @PostMapping()
    fun add(@RequestBody newEntity: ApplianceEntity): List<ApplianceEntity> {
        calls.save(newEntity)
        return calls.getAll()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): List<ApplianceEntity> {
        calls.delete(id)
        return calls.getAll()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentError(e: IllegalArgumentException) = e.message

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRuntimeError(e: IllegalArgumentException) = e.message
}