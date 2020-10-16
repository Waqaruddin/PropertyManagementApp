package com.example.propertymanagementapp.ui.property

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.models.ImageResponse
import com.example.propertymanagementapp.data.network.MyApi
import com.example.propertymanagementapp.databinding.ActivityAddPropertyBinding
import com.example.propertymanagementapp.helpers.SessionManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_add_property.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

class AddPropertyActivity : AppCompatActivity(), PropertyListener {
    lateinit var sessionManager: SessionManager
    private val CAMERA_REQUEST_CODE = 100
    private val IMAGE_PICK_CODE = 101
    var uriPath: String? = null
    lateinit var binding:ActivityAddPropertyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_add_property)
        sessionManager = SessionManager(this)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_property)
        var viewModel = ViewModelProviders.of(this).get(PropertyViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.propertyListener = this

        init()
    }

    private fun setupToolbar(){
        var toolbar = tool_bar
        toolbar.title = "Add Property"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {
        setupToolbar()
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        bottomSheetDialog.setContentView(view)

        button_add_photo.setOnClickListener {
            bottomSheetDialog.show()
        }
        view.button_open_camera.setOnClickListener {
            checkForCameraPermission()
            bottomSheetDialog.dismiss()
        }
        view.button_open_gallery.setOnClickListener {
            checkForGalleryPermission()
            bottomSheetDialog.dismiss()

        }
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
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "permission denied", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(applicationContext, "permission granted", Toast.LENGTH_SHORT)
                        .show()
                    openCamera()

                }
            }
            IMAGE_PICK_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "permission denied", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(applicationContext, "permission granted", Toast.LENGTH_SHORT)
                        .show()
                    openGallery()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            image_view.setImageURI(data?.data)
            //uri path
            uriPath = getRealPathFromURI(data?.data)
             uploadImage(uriPath!!)

            //Log.d("abc", uriPath.toString())
        }
        if ( resultCode == Activity.RESULT_OK &&  requestCode == CAMERA_REQUEST_CODE) {

            var bmp = data?.extras!!.get("data") as Bitmap
           image_view.setImageBitmap(bmp)
            var uri = getImageUri(this, bmp)
             uriPath =   getRealPathFromURI(uri)
            uploadImage(uriPath!!)
        }
    }

    override fun onStarted() {
        Toast.makeText(this, "Adding property started", Toast.LENGTH_SHORT).show()

    }

    override fun onSuccess(response: LiveData<String>) {
        response.observe(this, Observer {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, PropertyListActivity::class.java))
        })
    }

    override fun failure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // using retrofit and api
    fun uploadImage(path: String) {
        var file = File(path)
        var requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file)
        var body = MultipartBody.Part.createFormData("image", file.name, requestFile)
        MyApi().uploadImage(body)
            .enqueue(object : Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>?,
                    response: Response<ImageResponse>?
                ) {
                    if (response!!.isSuccessful) {
                        Log.d("aaa", response.body().data.location.toString())
                        binding.viewModel!!.addImage(response.body()!!.data.location!!)
                    }
                }

                override fun onFailure(call: Call<ImageResponse>?, t: Throwable?) {

                }

            })
    }

    // get actual path
    fun getRealPathFromURI(uri: Uri?): String? {
        val cursor: Cursor? = contentResolver.query(uri!!, null, null, null, null)
        cursor!!.moveToFirst()
        val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }

    // get URI from bitmap
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
       // fixMediaDir()
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String =
            MediaStore.Images.Media.insertImage(
                inContext.contentResolver,
                inImage,
                "Title",
                null
            )
        return Uri.parse(path)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }

//    fun fixMediaDir() {
//        val sdcard: File = Environment.getExternalStorageDirectory()
//        if (sdcard != null) {
//            val mediaDir = File(sdcard, "DCIM/Camera")
//            if (!mediaDir.exists()) {
//                mediaDir.mkdirs()
//            }
//        }
//    }
}