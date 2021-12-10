package com.masai.calendertaskmanager.api.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masai.calendertaskmanager.api.model.response.TaskDetail

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(tasks: TaskDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTasks(tasks: List<TaskDetail>)

    @Query("select * from tasks_table")
    fun getAllTasks(): LiveData<List<TaskDetail>>

    @Query("delete from tasks_table")
    fun deleteAllTasks()

    @Delete
    fun deleteTask(task: TaskDetail)
}