package com.example.lab1_kotlinapp

data class Person(
    val name: String = "",
    val role: String = "",
    val photo:String =""
) {
    constructor() : this("", "","")
}