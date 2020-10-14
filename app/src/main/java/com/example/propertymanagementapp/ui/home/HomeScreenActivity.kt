package com.example.propertymanagementapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.ui.property.AddPropertyActivity
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        init()
    }

    private fun init() {
        image_view_prop.setOnClickListener(this)
        image_view_todo_list.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            image_view_prop -> {
                startActivity(Intent(this, AddPropertyActivity::class.java))
            }

            image_view_todo_list -> {
                startActivity(Intent(this, ToDoListActivity::class.java))
            }


        }
    }
}