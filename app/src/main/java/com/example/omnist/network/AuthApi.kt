package com.example.omnist.network

import com.example.omnist.data.login.LoginData
import com.example.omnist.data.projectData.ProjectEntity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val okHttp = OkHttpClient.Builder()
    .addInterceptor(logger)
private const val BASE_URL = "http://omnisttechhubsolutions.com/"

interface AuthApi {


    //FOR Login
    @Headers("Content-Type: application/json")
    @POST("royalnest/api/login")
    suspend fun LoginApi(@Body authValBody: loginBody): Response<LoginData>

    //FOR GET USER PROFILE
    @GET("royalnest/api/myProjectList")
    suspend fun ProjectDataApi(@Header("token") token: String): Response<ProjectEntity>


    companion object {
        val gson = Gson()
        operator fun invoke(): AuthApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp.build())
                .build()
                .create(AuthApi::class.java)
        }
    }
}


class loginBody(
    email: String, password: String?
) {
    val email: String = email
    val password: String? = password

}