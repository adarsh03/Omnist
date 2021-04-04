package com.csestateconnect.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.omnist.data.login.LoginData
import com.example.omnist.data.projectData.ProjectEntity


// DAO CAN BE ABSTRACT CLASS OR INTERFACE
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)
}


@Dao
interface LoginDao: BaseDao<LoginData> {
    @Query("DELETE FROM login_table")
    fun delete()

    @Query("SELECT * from login_table")
    @JvmSuppressWildcards
    fun getAllData(): List<LoginData>
}




@Dao
interface ProjectDataDao: BaseDao<ProjectEntity> {
    @Query("DELETE FROM projectData_table")
     fun delete()

    @Query("SELECT * from projectData_table")
    @JvmSuppressWildcards
     fun getAllData(): LiveData<List<ProjectEntity>>
}

