package com.example.lab1_kotlinapp

data class Address(
    val addressLine1: String = "",
    val addressLine2: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val userId: String ="",
    val cardName:String="",
    val cardNumber:String="",
    val cardDate:String="",

    ){
    constructor() : this("", "","","","","","","","")
}