package com.masai.calendertaskmanager.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.masai.calendertaskmanager.R
import com.masai.calendertaskmanager.api.model.request.RequestGetTask
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: TaskViewModel by viewModels()
    private var tasksList = ArrayList<TaskDetail>()
    val TAG = "MainActivity-1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestGetTask = RequestGetTask(1010)
        viewModel.getTaskFromRemote(requestGetTask)
        viewModel.getAllTasks().observe(this, Observer {
            tasksList = it as ArrayList<TaskDetail>
            for (i in tasksList) {
                Log.d(TAG, "onCreate: $i")
            }
        })
    }
}