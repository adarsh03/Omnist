package com.example.omnist.utils

import androidx.room.TypeConverter
import com.example.omnist.data.projectData.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ProjectTypeConvertor {

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Data?> {
        if (data == null) {
            return Collections.emptyList() }

        val listType = object : TypeToken<List<Data?>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects1: List<Data?>): String {
        return Gson().toJson(someObjects1)
    }

}