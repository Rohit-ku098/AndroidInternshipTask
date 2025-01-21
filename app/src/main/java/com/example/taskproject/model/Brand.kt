package com.example.taskproject.model

import java.io.Serializable

data class Brand(
    val name: String,
    val image: Int,
    val link: String,
    val coupon: String
) : Serializable
