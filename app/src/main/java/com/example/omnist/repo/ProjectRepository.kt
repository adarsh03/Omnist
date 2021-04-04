package com.example.omnist.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.omnist.data.projectData.ProjectEntity
import com.example.omnist.db.MyDatabase
import com.example.omnist.network.AuthApi
import com.example.omnist.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProjectRepository
    (application: Application) {

    val database: MyDatabase

    lateinit var token: String

    init {
        database = MyDatabase.getInstance(
            application.applicationContext)

                    Coroutines.io {
                this.database.loginDao.getAllData().forEach {
                    token = it.data?.authKey.toString()
                }
            }
    }


    suspend fun projectDataApiCall(): Response<ProjectEntity>? {

        val responseVal = AuthApi().ProjectDataApi(token)

        when (responseVal.isSuccessful) {
            true -> {
                withContext(Dispatchers.IO) {
                    database.projectDataDao.delete()
                    database.projectDataDao.insert(responseVal.body()!!)

                    //For logging the response stored in database.
                    database.loginDao.getAllData().forEach {
                        Log.i("tagdbdataVal", "Id = ${it.id}, Token =  ${it.data}")

                    }

                }
            }
        }
        return responseVal
    }

    fun getProjectsFromDatabase(): LiveData<List<ProjectEntity>> {
        return database.projectDataDao.getAllData()
    }
}