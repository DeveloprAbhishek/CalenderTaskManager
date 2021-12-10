package com.masai.calendertaskmanager.api.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masai.calendertaskmanager.api.model.TaskDetail

@Database(entities = [TaskDetail::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getDatabaseObject(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db")
                INSTANCE = builder.build()
            }
            return INSTANCE!!
        }
    }
}