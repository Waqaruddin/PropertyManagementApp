package com.example.propertymanagementapp.ui.home

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.helpers.SessionManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.bottom_sheet.view.*

class HomeScreenActivity : AppCompatActivity(){
    lateinit var sessionManager: SessionManager
    private val CAMERA_REQUEST_CODE = 100
    private val IMAGE_PICK_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        sessionManager = SessionManager(this)
        init()
    }

    private fun init() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        bottomSheetDialog.setContentView(view)

        button_add_photo.setOnClickListener{
            bottomSheetDialog.show()
        }
        view.button_open_camera.setOnClickListener{
            checkForCameraPermission()
        }
        view.button_open_gallery.setOnClickListener{
            checkForGalleryPermission()
        }
        button_logout.setOnClickListener{
            dialogLogout()
        }
    }

    private fun dialogLogout() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setNegativeButton("No", object:DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }

        })
        builder.setPositiveButton("Yes", object:DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                sessionManager.logout()
                startActivity(Intent(applicationContext, LoginOrRegisterActivity::class.java))

            }

        })
        var mAlertDialog = builder.create()
        mAlertDialog.show()
    }


    private fun checkForGalleryPermission() {
        var permission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestGalleryPermission()
        } else {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun requestGalleryPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            IMAGE_PICK_CODE
        )
    }

    private fun checkForCameraPermission() {

        var permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(applicationContext, "permission denied", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(applicationContext, "permission granted", Toast.LENGTH_SHORT).show()

                }
            }
            IMAGE_PICK_CODE ->{
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(applicationContext, "permission denied", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "permission granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            image_view.setImageURI(data?.data)
        }
    }
}