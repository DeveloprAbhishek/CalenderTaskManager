package com.masai.calendertaskmanager.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.masai.calendertaskmanager.api.model.response.TaskDetail
import com.masai.calendertaskmanager.ui.OnClickListener
import kotlinx.android.synthetic.main.item_task_layout.view.*

class TaskViewHolder(itemView: View, var onClickListener: OnClickListener) :RecyclerView.ViewHolder(itemView) {

    fun setData(task: TaskDetail) {
        itemView.apply {
            tvTitle.text = task.title
            tvDate.text = task.date
            tvDesc.text = task.description
            ivDelete.setOnClickListener {
                task.id?.let { it1 -> onClickListener.onClickListener(task) }
            }
        }
    }
}