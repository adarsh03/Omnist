package com.example.omnist.utils

import androidx.room.TypeConverter
import com.example.omnist.data.login.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class LoginTypeConvertor {

    @TypeConverter
    fun fromStringCity(value: String?): Data? {
        val listType = object : TypeToken<Data?>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromListCity(list: Data?): String? {
        return Gson().toJson(list)
    }


}