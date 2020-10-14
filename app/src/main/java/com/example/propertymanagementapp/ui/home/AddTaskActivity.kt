package com.example.propertymanagementapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.models.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        init()
    }

    private fun init() {
        button_add_task.setOnClickListener {
            var title = edit_text_task.text.toString()
            var description = edit_text_decsription.text.toString()

            if (title != "") {
                var task = Task(title, description)
                var databaseReference = FirebaseDatabase.getInstance().getReference("tasks")

                var taskId = databaseReference.push().key
                databaseReference.child(taskId!!).setValue(task)
                Toast.makeText(applicationContext, "Task has been inserted", Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                Toast.makeText(applicationContext, "Title is required", Toast.LENGTH_SHORT).show()
            }


        }
    }
}
