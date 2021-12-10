package com.masai.calendertaskmanager.api.model


import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("task_detail")
    var taskDetail: TaskDetail,
    @SerializedName("task_id")
    var taskId: Int
)