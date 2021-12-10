package com.masai.calendertaskmanager.api.model.response


import com.google.gson.annotations.SerializedName

data class GetTaskResponse(
    @SerializedName("tasks")
    var tasks: List<Task>
)