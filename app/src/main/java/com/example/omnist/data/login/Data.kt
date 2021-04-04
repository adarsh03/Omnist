package com.example.omnist.data.login


import androidx.room.TypeConverters
import com.example.omnist.utils.LoginTypeConvertor
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("auth_key")
    val authKey: String?,
    val birthday: String?,
    val date: String?,
    @SerializedName("device_token")
    val deviceToken: String?,
    @SerializedName("device_type")
    val deviceType: String?,
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    val image: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("mobile_number")
    val mobileNumber: String?,
    val name: String?,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("user_tpye")
    val userTpye: String?
)