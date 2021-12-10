package com.masai.calendertaskmanager.api.model.request


import com.google.gson.annotations.SerializedName

data class RequestGetTask(
    @SerializedName("user_id")
    var userId: Int
)