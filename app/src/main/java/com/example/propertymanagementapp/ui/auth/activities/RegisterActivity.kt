package com.example.propertymanagementapp.ui.auth.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.ui.adapters.AdapterFragment
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var adapterFragments:AdapterFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    private fun init() {

        adapterFragments = AdapterFragment(supportFragmentManager)
        view_pager.adapter = adapterFragments
        tab_layout.setupWithViewPager(view_pager)
    }
}