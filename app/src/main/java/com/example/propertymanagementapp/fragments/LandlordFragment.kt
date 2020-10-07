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
import com.example.propertymanagementapp.models.Landlord
import com.example.propertymanagementapp.models.RegisterResponse
import kotlinx.android.synthetic.main.fragment_landlord.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LandlordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_landlord, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.button_register.setOnClickListener {
            var api = MyApi()
            var name = view.edit_text_name.text.toString()
            var email = view.edit_text_email.text.toString()
            var password = view.edit_text_password.text.toString()
            var type = "landlord"

            if (password.length == 6) {
                var landlord = Landlord(email = email, name = name, password = password, type = type)
                api.registerLandlord(landlord)
                    .enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            Toast.makeText(
                                activity,
                                "Registeration Successful!",
                                Toast.LENGTH_SHORT
                            ).show()
                            context!!.startActivity(Intent(context, LoginActivity::class.java))
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            Toast.makeText(activity, "Registeration Failed", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
            } else {
                Toast.makeText(activity, "Password must be 6 characters long", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        view.text_view_click_here.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }

    }


}





