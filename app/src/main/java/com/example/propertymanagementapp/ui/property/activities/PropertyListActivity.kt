package com.example.propertymanagementapp.ui.property.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.ui.adapters.AdapterPropertyList
import com.example.propertymanagementapp.data.models.MyProperty
import com.example.propertymanagementapp.databinding.ActivityPropertyListBinding
import com.example.propertymanagementapp.ui.property.GetPropertyListener
import com.example.propertymanagementapp.ui.property.PropertyViewModel
import kotlinx.android.synthetic.main.activity_property_list.*
import kotlinx.android.synthetic.main.app_bar.*

class PropertyListActivity : AppCompatActivity(), GetPropertyListener {
    private var adapterProperty:AdapterPropertyList? = null
    var mList:ArrayList<MyProperty> = ArrayList()
    lateinit var mBinding:ActivityPropertyListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_property_list)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_property_list)
        init()
    }


    private fun setupToolbar(){
        var toolbar = tool_bar
        toolbar.title = "Property List"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {

        setupToolbar()
        val viewModel = ViewModelProviders.of(this).get(PropertyViewModel::class.java)
        mBinding.viewModel = viewModel
        viewModel.getPropertyListener = this

        adapterProperty = AdapterPropertyList(this, mList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapterProperty

        button_add_property.setOnClickListener {
            startActivity(Intent(this, AddPropertyActivity::class.java))
        }
    }

    override fun onStarted() {
        Toast.makeText(this, "Starting now", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(response: LiveData<ArrayList<MyProperty>>) {
        response.observe(this, Observer {
            adapterProperty?.setData(it)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        })
    }

    override fun failure(message: String) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}