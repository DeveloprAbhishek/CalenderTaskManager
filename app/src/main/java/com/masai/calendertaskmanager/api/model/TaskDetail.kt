package com.masai.calendertaskmanager.api.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks_table")
data class TaskDetail(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    @SerializedName("date")
    var date: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("title")
    var title: String
)