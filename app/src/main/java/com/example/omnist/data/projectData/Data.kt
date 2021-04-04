package com.example.omnist.data.projectData


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("customer_id")
    val customerId: String?,
    val flats: String?,
    @SerializedName("floor_id")
    val floorId: String?,
    val floors: String?,
    val id: Int?,
    @SerializedName("location_id")
    val locationId: String?,
    @SerializedName("location_name")
    val locationName: String?,
    @SerializedName("project_id")
    val projectId: String?,
    @SerializedName("Project_name")
    val projectName: String?,
    val tower: String?,
    @SerializedName("tower_id")
    val towerId: String?
)