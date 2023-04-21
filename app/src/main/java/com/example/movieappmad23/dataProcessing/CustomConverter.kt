package com.example.movieappmad23.dataProcessing

import androidx.room.TypeConverter

class CustomConverter {

    @TypeConverter
    fun listToString(value: List<String>) = value.joinToString(",")

    @TypeConverter
    fun stringToList(value: String) = value.split(",").map{ it.trim()}
}