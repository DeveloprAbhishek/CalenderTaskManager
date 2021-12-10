package com.masai.calendertaskmanager.api.model.request


import com.google.gson.annotations.SerializedName

data class RequestToStoreTask(
    @SerializedName("task")
    var task: Task,
    @SerializedName("user_id")
    var userId: Int
)