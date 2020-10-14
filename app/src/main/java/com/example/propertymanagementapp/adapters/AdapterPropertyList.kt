package com.example.propertymanagementapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.models.MyProperty
import kotlinx.android.synthetic.main.row_adapter_property_list.view.*

class AdapterPropertyList(var mContext: Context, var mList: ArrayList<MyProperty>) :
    RecyclerView.Adapter<AdapterPropertyList.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(mContext).inflate(R.layout.row_adapter_property_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var property = mList[position]
        holder.bind(property)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(l: ArrayList<MyProperty>) {
        mList = l
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(property: MyProperty) {
            itemView.text_view_address.text = property.address
            itemView.text_view_city.text = property.city
            itemView.text_view_state.text = property.state

        }
    }

}