package com.example.propertymanagementapp.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.helpers.SessionManager
import com.example.propertymanagementapp.ui.property.activities.PropertyListActivity
import com.example.propertymanagementapp.ui.todolist.ToDoListActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.app_bar.*

class HomeScreenActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    lateinit var navView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        sessionManager = SessionManager(this)
        init()
    }

    private fun setupToolbar() {
        var toolbar = tool_bar
        toolbar.title = "Home"
    }


    private fun init() {
        setupToolbar()
        navView = nav_view
        drawerLayout = drawer_layout
        image_view_prop.setOnClickListener(this)
        image_view_todo_list.setOnClickListener(this)
        navView.setNavigationItemSelectedListener(this)


        var toggle = ActionBarDrawerToggle(this, drawerLayout, tool_bar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()

        }
    }

    override fun onClick(view: View?) {
        when (view) {
            image_view_prop -> {
                startActivity(Intent(this, PropertyListActivity::class.java))
            }

            image_view_todo_list -> {
                startActivity(Intent(this, ToDoListActivity::class.java))
            }


        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_logout -> {
                if (sessionManager.isLoggedIn()) {
                    dialogLogout()
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()
                }

            }
            R.id.item_account -> Toast.makeText(this, "account", Toast.LENGTH_SHORT).show()
            R.id.item_settings -> Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun dialogLogout() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }

        })
        builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                sessionManager.logout()
                startActivity(Intent(applicationContext, LoginOrRegisterActivity::class.java))

            }

        })
        var mAlertDialog = builder.create()
        mAlertDialog.show()
    }
}
