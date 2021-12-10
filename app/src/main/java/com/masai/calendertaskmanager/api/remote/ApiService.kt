package com.masai.calendertaskmanager.api.remote

import com.masai.calendertaskmanager.api.model.response.GetTaskResponse
import com.masai.calendertaskmanager.api.model.request.RequestGetTask
import com.masai.calendertaskmanager.api.model.request.RequestToDeleteTask
import com.masai.calendertaskmanager.api.model.request.RequestToStoreTask
import com.masai.calendertaskmanager.api.model.response.StoreTaskResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/getCalendarTaskLists")
    suspend fun getCalenderTaskList(@Body requestBody: RequestGetTask) : GetTaskResponse


    @POST("api/storeCalendarTask")
    suspend fun setCalenderTask(@Body requestBody: RequestToStoreTask) : StoreTaskResponse

    @POST("api/deleteCalendarTask")
    suspend fun deleteCalenderTask(@Body requestBody: RequestToDeleteTask) : StoreTaskResponse
}