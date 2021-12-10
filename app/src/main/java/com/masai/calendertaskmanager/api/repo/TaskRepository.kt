package com.masai.calendertaskmanager.api.repo

import androidx.lifecycle.LiveData
import com.masai.calendertaskmanager.api.local.TaskDao
import com.masai.calendertaskmanager.api.model.GetTaskResponse
import com.masai.calendertaskmanager.api.model.TaskDetail
import com.masai.calendertaskmanager.api.remote.ServiceGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

class TaskRepository @Inject constructor(val taskDao: TaskDao){

    fun getRemoteTasks(requestBody: RequestBody) {
        lateinit var response : GetTaskResponse
        CoroutineScope(Dispatchers.IO).launch {
            response = ServiceGenerator.getApiService().getCalenderTaskList(requestBody)
            saveToDB(response)
        }
    }

    private fun saveToDB(response: GetTaskResponse) {
        var list  = ArrayList<TaskDetail>()
        response.tasks.forEach {
        val task = TaskDetail(
                it.taskDetail.id,
                it.taskDetail.date,
                it.taskDetail.description,
                it.taskDetail.title
            )
            list.add(task)
        }
        taskDao.deleteAllTasks()
        taskDao.addAllTasks(list)
    }

    fun getTaskListFromDb(): LiveData<List<TaskDetail>> {
        return taskDao.getAllTasks()
    }
}