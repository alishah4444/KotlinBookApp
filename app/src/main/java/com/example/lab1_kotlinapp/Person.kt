package com.example.lab1_kotlinapp

data class Person(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
) {
    constructor() : this("", "","","")
}