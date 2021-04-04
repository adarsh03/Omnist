package com.example.omnist.data.login


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.omnist.utils.LoginTypeConvertor
import com.google.gson.annotations.SerializedName

@TypeConverters(LoginTypeConvertor::class)
@Entity(tableName = "login_table")

data class LoginData(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val `data`: Data?,
    val message: String?,
    @SerializedName("response_code")
    val responseCode: Int?,
    @SerializedName("response_status")
    val responseStatus: String?
)