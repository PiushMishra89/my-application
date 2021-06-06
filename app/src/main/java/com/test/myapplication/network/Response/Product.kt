package com.test.myapplication.network.Response

data class Product(
    val date_added: String,
    val id: Int,
    val name: String,
    val tax: Tax,
    val variants: List<Variant>
)