package com.example.propertymanagementapp.ui.property

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.adapters.AdapterPropertyList
import com.example.propertymanagementapp.data.models.MyProperty
import com.example.propertymanagementapp.databinding.ActivityPropertyListBinding
import io.reactivex.internal.operators.maybe.MaybeFlatMapBiSelector
import kotlinx.android.synthetic.main.activity_property_list.*

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

    private fun init() {

        val viewModel = ViewModelProviders.of(this).get(PropertyViewModel::class.java)
        mBinding.viewModel = viewModel
        viewModel.getPropertyListener = this

        adapterProperty = AdapterPropertyList(this, mList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapterProperty
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
}