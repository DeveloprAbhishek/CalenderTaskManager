package com.masai.calendertaskmanager.api.remote

import com.masai.calendertaskmanager.api.model.GetTaskResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/getCalendarTaskLists")
    suspend fun getCalenderTaskList(@Body requestBody: RequestBody) : GetTaskResponse
}