package com.example.area51.activity 

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.area51.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_inicial.*
import java.io.ByteArrayOutputStream


class InicialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        view.visibility = View.INVISIBLE

        img51.setOnClickListener{
            view.visibility = View.VISIBLE
            view.background = img51.drawable
        }
         upload.setOnClickListener {

             val storage = Firebase.storage
             val storageReference = storage.reference
             val imgReference = storageReference.child("fotos/").child("area51.jpg")

             img51.isDrawingCacheEnabled = true
             img51.buildDrawingCache()

             val bitMap = (img51.drawable as BitmapDrawable).bitmap
             val baos = ByteArrayOutputStream()
             bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

             val data = baos.toByteArray()

             val upLoadTask = imgReference.putBytes(data)


             with(upLoadTask) {

                 addOnFailureListener {
                     val mensagem = it.message
                     Toast.makeText(this@InicialActivity, mensagem, Toast.LENGTH_LONG).show()
                 }
                 addOnCompleteListener {
                     Toast.makeText(this@InicialActivity, "Sucesso", Toast.LENGTH_LONG).show()
                 }
             }


         }
     }
    }