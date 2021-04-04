package com.example.omnist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.csestateconnect.db.LoginDao
import com.csestateconnect.db.ProjectDataDao
import com.example.omnist.data.login.LoginData
import com.example.omnist.data.projectData.ProjectEntity

@Database(entities = [LoginData::class , ProjectEntity::class ], version = 10, exportSchema = false)

abstract class MyDatabase : RoomDatabase() {

    abstract val loginDao: LoginDao
    abstract val projectDataDao: ProjectDataDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "database_name"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
