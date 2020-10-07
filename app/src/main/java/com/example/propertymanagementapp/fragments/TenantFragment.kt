package com.example.propertymanagementapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.activities.LoginActivity
import com.example.propertymanagementapp.api.MyApi
import com.example.propertymanagementapp.models.RegisterResponse
import com.example.propertymanagementapp.models.Tenant
import kotlinx.android.synthetic.main.fragment_tenant.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TenantFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_tenant, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        var api = MyApi()
        view.button_register.setOnClickListener {
            var landlordEmail = view.edit_text_landlord_email.text.toString()
            var email = view.edit_text_email.text.toString()
            var name = view.edit_text_name.text.toString()
            var password = view.edit_text_password.text.toString()
            var type = "tenant"

            if(password.length == 6){
                var tenant = Tenant(email, landlordEmail, name, password, type)
                api.registerTenant(tenant)
                    .enqueue(object:Callback<RegisterResponse>{
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            Toast.makeText(activity, "Registeration Successful", Toast.LENGTH_SHORT).show()
                            context!!.startActivity(Intent(context, LoginActivity::class.java))
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            Toast.makeText(activity, "Registeration Successful", Toast.LENGTH_SHORT).show()

                        }

                    })
            }else{
                Toast.makeText(activity, "password must be 6 characters long", Toast.LENGTH_SHORT).show()
            }

        }
        view.text_view_click_here.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }

    }


}