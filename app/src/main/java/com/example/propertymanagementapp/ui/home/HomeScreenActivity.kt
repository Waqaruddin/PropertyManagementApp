package com.example.propertymanagementapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.ui.property.AddPropertyActivity
import com.example.propertymanagementapp.ui.property.PropertyListActivity
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.app_bar.*

class HomeScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        init()
    }

    private fun setupToolbar(){
        var toolbar = tool_bar
        toolbar.title = "Home"
    }


    private fun init() {
        setupToolbar()
        image_view_prop.setOnClickListener(this)
        image_view_todo_list.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            image_view_prop -> {
                startActivity(Intent(this, PropertyListActivity::class.java))
            }

            image_view_todo_list -> {
                startActivity(Intent(this, ToDoListActivity::class.java))
            }


        }
    }
}