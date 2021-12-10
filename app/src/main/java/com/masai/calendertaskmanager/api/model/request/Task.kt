package com.masai.calendertaskmanager.api.model.request


import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("date")
    var date: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("title")
    var title: String
)