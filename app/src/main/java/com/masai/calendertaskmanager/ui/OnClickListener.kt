package com.masai.calendertaskmanager.ui

import com.masai.calendertaskmanager.api.model.response.TaskDetail

interface OnClickListener {
    fun onClickListener(task: TaskDetail)
}