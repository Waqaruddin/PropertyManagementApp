package com.example.propertymanagementapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.propertymanagementapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        init()
    }

    private fun init() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        bottomSheetDialog.setContentView(view)
        button_add_photo.setOnClickListener {
            bottomSheetDialog.show()
        }
    }
}