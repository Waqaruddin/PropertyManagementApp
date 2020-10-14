package com.example.propertymanagementapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.adapters.AdapterTodoList
import com.example.propertymanagementapp.data.models.Task
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_to_do_list.*
import kotlinx.android.synthetic.main.app_bar.*

class ToDoListActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    var mList: ArrayList<Task> = ArrayList()
    lateinit var navView: NavigationView
    var userEmail: String? = null
    lateinit var drawerLayout: DrawerLayout
    var keysList: ArrayList<String> = ArrayList()
    var auth = FirebaseAuth.getInstance()
    private var adapterTask: AdapterTodoList? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)
        databaseReference = FirebaseDatabase.getInstance().getReference(Task.COLLECTION_NAME)
        init()

    }

    private fun setupToolbar(){
        var toolbar = tool_bar
        toolbar.title = "To-do List"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }


    private fun init() {
        setupToolbar()
        getData()
        adapterTask = AdapterTodoList(this, mList, keysList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapterTask

        button_add_task.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    private fun getData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mList.clear()
                keysList.clear()
                for (data in dataSnapshot.children) {
                    var task = data.getValue(Task::class.java)
                    var key = data.key
                    mList.add(task!!)
                    keysList.add(key!!)
                }
                adapterTask!!.setData(mList)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}