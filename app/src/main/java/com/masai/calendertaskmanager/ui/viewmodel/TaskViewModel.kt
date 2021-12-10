package com.masai.calendertaskmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masai.calendertaskmanager.api.model.TaskDetail
import com.masai.calendertaskmanager.api.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(val repo: TaskRepository): ViewModel() {

    fun getTaskFromRemote(requestBody: RequestBody) {
        repo.getRemoteTasks(requestBody)
    }

    fun getAllTasks(): LiveData<List<TaskDetail>> {
        return repo.getTaskListFromDb()
    }

}