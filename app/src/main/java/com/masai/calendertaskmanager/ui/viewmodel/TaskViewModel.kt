package com.masai.calendertaskmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masai.calendertaskmanager.api.model.request.RequestGetTask
import com.masai.calendertaskmanager.api.model.request.RequestToStoreTask
import com.masai.calendertaskmanager.api.model.response.StoreTaskResponse
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.api.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(val repo: TaskRepository): ViewModel() {

    fun setTask(requestToStoreTask: RequestToStoreTask) {
        repo.setCalenderTask(requestToStoreTask)
    }

    fun getTaskFromRemote(requestBody: RequestGetTask) {
        repo.getRemoteTasks(requestBody)
    }

    fun getAllTasks(): LiveData<List<TaskDetail>> {
        return repo.getTaskListFromDb()
    }
    fun deleteTask(taskDetail: TaskDetail) {
        repo.deleteTask(taskDetail)
    }

}