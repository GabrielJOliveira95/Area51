package com.example.area51.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import com.example.area51.R
import com.example.area51.helper.PermJA
import com.example.area51.model.GetFirebaseService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_up_load_foto.*
import java.io.ByteArrayOutputStream
import java.security.Permission
import java.util.jar.Manifest

class UpLoadFotoActivity : AppCompatActivity() {
    private val arrayOfPermission = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_load_foto)

        PermJA.permi(arrayOfPermission, this, 1)

        btn_up_load_foto.setOnClickListener {
            val storage = GetFirebaseService.getStorageReference()
            val imageReference = storage?.child("fotos/")?.child("teste.jpg")

            img_teste.isDrawingCacheEnabled = true
            img_teste.buildDrawingCache()
            val bitmap = (img_teste.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val uploadTask = imageReference?.putBytes(data)

            with(uploadTask) {
                this!!.addOnFailureListener {
                    Log.i("ERRO UPLOAD FOTO", "${it.message}")
                }
                this!!.addOnCompleteListener {
                    Toast.makeText(applicationContext, "UPLOAD Concluído", Toast.LENGTH_LONG).show()
                }
            }
        }
        uploadCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 1)
            }
        }

        uploadGaleria.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            if (intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, 1)
            }
        }

    }


}
