package com.masai.calendertaskmanager.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.calendertaskmanager.R
import com.masai.calendertaskmanager.Utils.Constants.Companion.USER_ID
import com.masai.calendertaskmanager.api.model.request.RequestGetTask
import com.masai.calendertaskmanager.api.model.request.RequestToStoreTask
import com.masai.calendertaskmanager.api.model.request.Task
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.ui.OnClickListener
import com.masai.calendertaskmanager.ui.adapters.TaskAdapter
import com.masai.calendertaskmanager.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener {
    val viewModel: TaskViewModel by viewModels()
    private var tasksList = ArrayList<TaskDetail>()
    private lateinit var taskAdapter: TaskAdapter
    private var currentDate = ""
    val TAG = "MainActivity-1"

    //current Month Year
    private lateinit var calendar: Calendar
    private var currentYear by Delegates.notNull<Int>()
    private var currentMonth by Delegates.notNull<Int>()
    private var currentDay by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting current date and setting to date field
        calendar = Calendar.getInstance()
        currentYear = calendar.get(Calendar.YEAR)
        currentMonth = calendar.get(Calendar.MONTH)
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        var curMonth = currentMonth + 1
        var m = "$curMonth"
        var d = "$currentDay"
        if(curMonth < 10) {
            m = "0$curMonth"
        }

        if(currentDay < 10) {
            d = "0$currentDay"
        }
        currentDate = "$currentYear-$m-$d"

        btnAddTask.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            intent.putExtra("date", currentDate)
            startActivity(intent)
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val mon = month + 1
            var d = "$dayOfMonth"
            var m = "${mon}"

            if(dayOfMonth < 10) {
                d = "0$dayOfMonth"
            }
            if(mon < 10) {
                m = "0$mon"
            }
            currentDate = "$year-$m-$d"
            Log.d(TAG, "onCreate: $currentDate")
        }

        showAllTasks.setOnClickListener {
            val intent = Intent(this, ShowAllTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val requestGetTask = RequestGetTask(USER_ID)
        viewModel.getTaskFromRemote(requestGetTask)
        viewModel.getAllTasks().observe(this, Observer {
            tasksList = it as ArrayList<TaskDetail>
            setAdapter()
        })
    }

    private fun setAdapter() {
        taskAdapter = TaskAdapter(tasksList, this)
        rvShowTasks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
    }

    override fun onClickListener(task: TaskDetail) {
        viewModel.deleteTask(task)
        taskAdapter.notifyDataSetChanged()
    }
}