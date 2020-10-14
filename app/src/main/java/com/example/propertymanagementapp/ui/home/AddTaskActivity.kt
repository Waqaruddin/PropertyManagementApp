package com.example.propertymanagementapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.models.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.app_bar.*

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        init()
    }

    private fun setupToolbar(){
        var toolbar = tool_bar
        toolbar.title = "Add Task"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }

    private fun init() {

        setupToolbar()
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
