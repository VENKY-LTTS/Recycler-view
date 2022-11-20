package com.example.recyclerview

import com.google.gson.annotations.SerializedName

data class JsonDataItem(

    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)