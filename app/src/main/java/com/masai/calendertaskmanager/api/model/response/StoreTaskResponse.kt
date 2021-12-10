package com.masai.calendertaskmanager.api.model.response


import com.google.gson.annotations.SerializedName

data class StoreTaskResponse(
    @SerializedName("status")
    var status: String
)