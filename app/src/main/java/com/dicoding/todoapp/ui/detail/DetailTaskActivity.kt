package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action

        val factory = ViewModelFactory.getInstance(this)
        val taskViewModelDetail = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val taskId = extras.getInt(TASK_ID)

            taskViewModelDetail.setTaskId(taskId)
            taskViewModelDetail.task.observe(this){task ->
                populateTask(task)
                findViewById<Button>(R.id.btn_delete_task).setOnClickListener{
                    taskViewModelDetail.deleteTask()
                    finish()
                }
            }
        }

    }

    private fun populateTask(task: Task?) {
        val editTextTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val editTextDescription = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val editTextDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)

        if (task != null) {
            editTextTitle.setText(task.title)
            editTextDescription.setText(task.description)
            editTextDueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
        }
    }
}