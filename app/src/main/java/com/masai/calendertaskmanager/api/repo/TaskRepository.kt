package com.masai.calendertaskmanager.api.repo

import androidx.lifecycle.LiveData
import com.masai.calendertaskmanager.api.local.TaskDao
import com.masai.calendertaskmanager.api.model.response.GetTaskResponse
import com.masai.calendertaskmanager.api.model.request.RequestGetTask
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.api.remote.ServiceGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskRepository @Inject constructor(val taskDao: TaskDao){
    fun getRemoteTasks(requestBody: RequestGetTask) {
        lateinit var response : GetTaskResponse
        CoroutineScope(Dispatchers.IO).launch {
            response = ServiceGenerator.getApiService().getCalenderTaskList(requestBody)
            saveToDB(response)
        }
    }

    private fun saveToDB(response: GetTaskResponse) {
        var list  = ArrayList<TaskDetail>()
        response.tasks.forEach {
            list.add(it.taskDetail)
        }
        taskDao.deleteAllTasks()
        taskDao.addAllTasks(list)
    }

    fun getTaskListFromDb(): LiveData<List<TaskDetail>> {
        return taskDao.getAllTasks()
    }
}