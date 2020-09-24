package com.example.area51.activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.area51.R
import com.example.area51.helper.GetFirebaseService
import com.example.area51.helper.MyBase64
import com.example.area51.helper.MyCurrentDate
import kotlinx.android.synthetic.main.activity_up_load_foto.*
import java.io.ByteArrayOutputStream
import java.util.*

class UpLoadFotoActivity : AppCompatActivity() {
    private val PERMISSAO_CAMERA = 100
    private val PERMISSAO_GALERIA = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_load_foto)

        uploadCamera.setOnClickListener {

            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                ) -> {
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                        takePictureIntent.resolveActivity(packageManager)?.also {
                            startActivityForResult(takePictureIntent, PERMISSAO_CAMERA)
                        }
                    }

                }
                else -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.CAMERA),
                            PERMISSAO_CAMERA
                        )
                    }
                }
            }
        }

        uploadGaleria.setOnClickListener {

            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivityForResult(intent, PERMISSAO_GALERIA)
                        Toast.makeText(applicationContext, "Galeria", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            PERMISSAO_GALERIA
                        )
                    }
                }
            }


        }

        btn_up_load_foto.setOnClickListener {

            val user = GetFirebaseService.auth
            val email = user?.currentUser?.email.toString()
            val userId = MyBase64.codificar(email).replace("\n", "")

            val dataAtual = MyCurrentDate.dataAtual()

            val nomeDaFoto = UUID.randomUUID().toString()

            val storage = GetFirebaseService.getStorageReference()

            val imageReference = storage?.child("usuarios")?.child("$userId/")?.child("fotos/")?.child(dataAtual)?.child(nomeDaFoto)

            img_escolhida_upload.isDrawingCacheEnabled = true
            img_escolhida_upload.buildDrawingCache()
            val bitmap = (img_escolhida_upload.drawable as BitmapDrawable).bitmap
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


    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var imageBitmap: Bitmap

        if (requestCode == PERMISSAO_CAMERA && resultCode == RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            img_escolhida_upload.setImageBitmap(imageBitmap)
        }
        if (requestCode == PERMISSAO_GALERIA && resultCode == RESULT_OK) {
            val uri = data?.data
            val imagedecoder =  ImageDecoder.createSource(contentResolver, uri!!)
            imageBitmap = ImageDecoder.decodeBitmap(imagedecoder)
            img_escolhida_upload.setImageBitmap(imageBitmap)
        }
    }

    fun confirmarPermissoes() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Permissão Negada")
        alertDialog.setMessage("O aplicativo precisa da sua autorização para funcionar corretamente.")
        alertDialog.setCancelable(false)

        alertDialog.setPositiveButton(
            "Fechar e tentar novamente",
            DialogInterface.OnClickListener { _, _ ->
                finish()
            })

        alertDialog.create()
        alertDialog.show()
    }
}
