package com.example.lab1_kotlinapp

data class Book(
    val author: String = "",
    val country: String = "",
    val imageLink:String ="",
    val language:String="",
    val title:String="",
    val year:Int =2002
) {
    constructor() : this("", "","","","",1998)
}