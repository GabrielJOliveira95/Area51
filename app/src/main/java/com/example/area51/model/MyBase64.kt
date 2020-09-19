package com.example.area51.model

import android.util.Base64

class MyBase64 {

    companion object {

        fun codificar(txt: String): String {
            return Base64.encodeToString(txt.toByteArray(), Base64.DEFAULT).replace("\n||\r", "")
        }

        fun decodificar(txtCodificado: String): String {

            return String(Base64.decode(txtCodificado.toByteArray(), Base64.DEFAULT))
        }
    }
}