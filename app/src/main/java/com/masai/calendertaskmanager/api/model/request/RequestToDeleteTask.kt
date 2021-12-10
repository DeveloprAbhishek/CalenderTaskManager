package com.masai.calendertaskmanager.api.model.request


import com.google.gson.annotations.SerializedName

data class RequestToDeleteTask(
    @SerializedName("task_id")
    var taskId: Int,
    @SerializedName("user_id")
    var userId: Int
)