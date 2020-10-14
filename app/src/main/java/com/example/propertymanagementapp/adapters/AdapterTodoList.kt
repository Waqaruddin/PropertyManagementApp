package com.example.propertymanagementapp.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.models.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.row_adapter_todo_list.view.*

class AdapterTodoList(
    var mContext: Context,
    var mList: ArrayList<Task>,
    private var keysList: ArrayList<String>
) : RecyclerView.Adapter<AdapterTodoList.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, position: Int) {
            var databaseReference = FirebaseDatabase.getInstance().getReference("tasks")
            var status = task.status

            itemView.text_view_task_title.text = task.title
            itemView.text_view_task_description.text = task.description
            itemView.text_view_status.text = task.status

            itemView.check_box.setOnClickListener {
                if (status == "incomplete") {
                    databaseReference.child(keysList[position])
                        .setValue(Task(task.title, task.description, "complete"))
                    itemView.text_view_status.text = task.status
                } else {
                    databaseReference.child(keysList[position])
                        .setValue(Task(task.title, task.description, "incomplete"))
                    itemView.text_view_status.text = task.status
                }

            }


            itemView.button_delete.setOnClickListener {
                var builder = AlertDialog.Builder(mContext)
                builder.setTitle("Delete Task")
                builder.setMessage("Are you sure you want to delete this task?")
                builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, p1: Int) {
                        dialog?.dismiss()
                    }

                })
                builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, p1: Int) {
                        databaseReference.child(keysList[position]).setValue(null)
                        Toast.makeText(mContext, "Task deleted", Toast.LENGTH_SHORT).show()
                    }

                })
                var myAlertDialog = builder.create()
                myAlertDialog.show()

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(mContext).inflate(R.layout.row_adapter_todo_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var task = mList[position]
        holder.bind(task, position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(l: ArrayList<Task>) {
        mList = l
        notifyDataSetChanged()
    }
}

