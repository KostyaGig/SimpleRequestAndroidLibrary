package com.zinoview.firstkibrary

class User(
    private val name: String,
    private val age: Int
) {

    fun info() : String = "User: $name, age $age"
}