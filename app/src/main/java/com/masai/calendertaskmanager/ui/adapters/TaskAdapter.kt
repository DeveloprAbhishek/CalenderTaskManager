package com.masai.calendertaskmanager.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.calendertaskmanager.R
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.ui.OnClickListener
import com.masai.calendertaskmanager.ui.viewholders.TaskViewHolder

class TaskAdapter(var taskList: ArrayList<TaskDetail>, var onClickListener: OnClickListener): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val taskView = LayoutInflater.from(parent.context).inflate(R.layout.item_task_layout, parent, false)
        return TaskViewHolder(taskView, onClickListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.setData(task)
    }

    override fun getItemCount(): Int =  taskList.size
}