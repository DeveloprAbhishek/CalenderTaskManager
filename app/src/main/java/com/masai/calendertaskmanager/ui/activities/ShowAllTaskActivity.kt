package com.masai.calendertaskmanager.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.masai.calendertaskmanager.R
import com.masai.calendertaskmanager.api.model.request.RequestGetTask
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.ui.OnClickListener
import com.masai.calendertaskmanager.ui.adapters.TaskAdapter
import com.masai.calendertaskmanager.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_show_all_task.*

@AndroidEntryPoint
class ShowAllTaskActivity : AppCompatActivity(), OnClickListener {

    val viewModel: TaskViewModel by viewModels()
    private var tasksList = ArrayList<TaskDetail>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_task)
        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val requestGetTask = RequestGetTask(1010)
        viewModel.getTaskFromRemote(requestGetTask)
        viewModel.getAllTasks().observe(this, Observer {
            tasksList = it as ArrayList<TaskDetail>
            setAdapter()
        })
    }

    private fun setAdapter() {
        taskAdapter = TaskAdapter(tasksList, this)
        rvShowTasks.apply {
            layoutManager = LinearLayoutManager(this@ShowAllTaskActivity)
            adapter = taskAdapter
        }
    }

    override fun onClickListener(task: TaskDetail) {
        viewModel.deleteTask(task)
        taskAdapter.notifyDataSetChanged()
    }
}