package com.masai.calendertaskmanager.ui.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.masai.calendertaskmanager.R
import com.masai.calendertaskmanager.Utils.Constants.Companion.USER_ID
import com.masai.calendertaskmanager.api.model.request.RequestToStoreTask
import com.masai.calendertaskmanager.api.model.request.Task
import com.masai.calendertaskmanager.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_task.*
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class CreateTaskActivity : AppCompatActivity() {

    // creating view model instance
    val viewModel: TaskViewModel by viewModels()
    private var currentDate = ""

    //current Month Year
    private lateinit var calendar: Calendar
    private var currentYear by Delegates.notNull<Int>()
    private var currentMonth by Delegates.notNull<Int>()
    private var currentDay by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        //getting current date and setting to date field
        calendar = Calendar.getInstance()
        currentYear = calendar.get(Calendar.YEAR)
        currentMonth = calendar.get(Calendar.MONTH)
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        currentDate = intent.getStringExtra("date").toString()
        //set cur date
        etDate.setText(currentDate)

        //you can change date if you want to add task on another date
        etDate.setOnClickListener {

            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val mon = monthOfYear + 1
                var d = "$dayOfMonth"
                var m = "${mon}"

                if(dayOfMonth < 10) {
                    d = "0$dayOfMonth"
                }
                if(mon < 10) {
                    m = "0$mon"
                }
                etDate.setText("$year-$m-$d")
            }, currentYear, currentMonth, currentDay)

            datePicker.show()
        }


        //this method save the task on server
        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val date = etDate.text.toString()
            val desc = etDesc.text.toString()
            val newTask = Task(date = date, title = title, description = desc)
            val requestToStoreTask = RequestToStoreTask(newTask, USER_ID)
            viewModel.setTask(requestToStoreTask)
            finish()
        }
    }
}