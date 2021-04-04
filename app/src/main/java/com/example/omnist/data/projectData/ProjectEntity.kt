package com.example.omnist.data.projectData


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.omnist.utils.ProjectTypeConvertor
import com.google.gson.annotations.SerializedName


@TypeConverters(ProjectTypeConvertor::class)
@Entity(tableName = "projectData_table")

data class ProjectEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val data: List<Data>?,
    val message: String?,
    @SerializedName("response_code")
    val responseCode: Int?,
    @SerializedName("response_status")
    val responseStatus: String?
)