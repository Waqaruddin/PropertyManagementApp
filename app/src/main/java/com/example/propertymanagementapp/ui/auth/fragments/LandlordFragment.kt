package com.example.propertymanagementapp.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.databinding.FragmentLandlordBinding
import com.example.propertymanagementapp.helpers.toastShort
import com.example.propertymanagementapp.ui.auth.AuthListener
import com.example.propertymanagementapp.ui.auth.AuthViewModel
import com.example.propertymanagementapp.ui.auth.activities.LoginActivity


class LandlordFragment : Fragment(), AuthListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         //Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_landlord, container, false)
//        //init(view)
//        return view

        var binding:FragmentLandlordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_landlord, container, false)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
        return binding.root
    }

//    private fun init(view: View) {
//        view.button_register.setOnClickListener {
//            var api = MyApi()
//            var name = view.edit_text_name.text.toString()
//            var email = view.edit_text_email.text.toString()
//            var password = view.edit_text_password.text.toString()
//            var type = "landlord"
//
//            if (password.length == 6) {
//                var landlord = Landlord(email = email, name = name, password = password, type = type)
//                api.registerLandlord(landlord)
//                    .enqueue(object : Callback<RegisterResponse> {
//                        override fun onResponse(
//                            call: Call<RegisterResponse>,
//                            response: Response<RegisterResponse>
//                        ) {
//                            Toast.makeText(
//                                activity,
//                                "Registeration Successful!",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            context!!.startActivity(Intent(context, LoginActivity::class.java))
//                        }
//
//                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                            Toast.makeText(activity, "Registeration Failed", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//
//                    })
//            } else {
//                Toast.makeText(activity, "Password must be 6 characters long", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        view.text_view_click_here.setOnClickListener {
//            startActivity(Intent(activity, LoginActivity::class.java))
//        }
//
//    }

    override fun onStarted() {
        context!!.toastShort("Registration started")
    }

    override fun onSuccess(response: LiveData<String>) {
        response.observe(this, Observer {
            context!!.toastShort("Registration Successful")
            startActivity(Intent(context, LoginActivity::class.java))
        })
    }

    override fun failure(message: String) {
        context!!.toastShort(message)
    }


}





