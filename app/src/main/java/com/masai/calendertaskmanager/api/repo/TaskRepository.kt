package com.masai.calendertaskmanager.api.repo

import androidx.lifecycle.LiveData
import com.masai.calendertaskmanager.Utils.Constants.Companion.USER_ID
import com.masai.calendertaskmanager.api.local.TaskDao
import com.masai.calendertaskmanager.api.model.response.GetTaskResponse
import com.masai.calendertaskmanager.api.model.request.RequestGetTask
import com.masai.calendertaskmanager.api.model.request.RequestToDeleteTask
import com.masai.calendertaskmanager.api.model.request.RequestToStoreTask
import com.masai.calendertaskmanager.api.model.response.StoreTaskResponse
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.api.remote.ServiceGenerator
import com.masai.calendertaskmanager.di.TaskModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TaskRepository @Inject constructor(val taskDao: TaskDao) {
    val api = ServiceGenerator.getApiService()

    fun getRemoteTasks(requestBody: RequestGetTask) {
        lateinit var response: GetTaskResponse
        CoroutineScope(Dispatchers.IO).launch {
            response = api.getCalenderTaskList(requestBody)
            saveToDB(response)
        }
    }

    fun setCalenderTask(requestToStoreTask: RequestToStoreTask) {
        CoroutineScope(Dispatchers.IO).launch {
            api.setCalenderTask(requestToStoreTask)
        }
    }

    private fun saveToDB(response: GetTaskResponse) {
        var list = ArrayList<TaskDetail>()
        response.tasks.forEach {
            var task = TaskDetail(
                id = it.taskId,
                date = it.taskDetail.date,
                title = it.taskDetail.title,
                description = it.taskDetail.description
            )
            list.add(task)
        }
        taskDao.deleteAllTasks()
        taskDao.addAllTasks(list)
    }

    fun getTaskListFromDb(): LiveData<List<TaskDetail>> {
        return taskDao.getAllTasks()
    }

    fun deleteTask(task: TaskDetail) {
        CoroutineScope(Dispatchers.IO).launch {
            val deleteTask = task.id?.let { RequestToDeleteTask(it, USER_ID) }
            api.deleteCalenderTask(requestBody = deleteTask!!)
            taskDao.deleteTask(task)
        }


    }
}