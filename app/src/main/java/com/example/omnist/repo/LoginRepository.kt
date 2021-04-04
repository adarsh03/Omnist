package com.example.omnist.repo

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.example.omnist.data.login.LoginData
import com.example.omnist.db.MyDatabase
import com.example.omnist.network.AuthApi
import com.example.omnist.network.loginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository
    (application: Application) {

    val database: MyDatabase

    init {
        database = MyDatabase.getInstance(
            application.applicationContext
        )
    }


    suspend fun loginMethodCall(email: String, password: String): Response<LoginData>? {

        val responseVal = AuthApi().LoginApi(loginBody(email, password))

        when (responseVal.isSuccessful) {
            true -> {
                withContext(Dispatchers.IO) {
                    database.loginDao.delete()
                    database.loginDao.insert(responseVal.body()!!)

                    //For logging the response stored in database.
                    database.loginDao.getAllData().forEach {
                        Log.i("tagdbdataVal", "Id = ${it.id}, Token =  ${it.data}")
                    }

                }
            }
        }

        return responseVal
    }

}