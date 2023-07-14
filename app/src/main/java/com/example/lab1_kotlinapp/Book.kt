package com.example.lab1_kotlinapp

data class Book(
    val id: String="",
    val author: String = "",
    val country: String = "",
    val imageLink:String ="",
    val language:String="",
    val title:String="",
    val year:Int =2002,
    val pages:Int=0,
    val link:String="",
) {
    constructor() : this("", "","","","","",1998)
}