package com.example.area51.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.area51.R
import com.example.area51.model.GetFirebaseService
import com.example.area51.model.Usuario
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.android.synthetic.main.activity_logar.*

class LogarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logar)

        title = getString(R.string.logar)

        btn_logar.setOnClickListener {
            logar()
        }
    }


    fun validarCampos(): Boolean {

        val campoValidado: Boolean
        when {
            editTextEmail.text.toString().isEmpty() -> {
                textInputLayoutEmail.isErrorEnabled = true
                textInputLayoutEmail.error = "Digite um email válido"
                campoValidado = false
            }
            editTextSenha.text.toString().isEmpty() -> {
                textInputLayoutEmail.isErrorEnabled = false

                textInputLayoutSenha.error = "Digite sua senha"
                textInputLayoutSenha.isErrorEnabled = true
                campoValidado = false
            }
            else -> {
                textInputLayoutEmail.isErrorEnabled = false
                textInputLayoutSenha.isErrorEnabled = false
                campoValidado = true
            }
        }
        return campoValidado
    }

    fun logar() {

        val email = editTextEmail.text.toString()
        val senha = editTextSenha.text.toString()
        val user = Usuario()
        val auth = GetFirebaseService.getFireBaseAuth()
        user.email = email
        user.senha = senha

        if (validarCampos()) {

            auth?.signInWithEmailAndPassword(user.email!!, user.senha!!)?.addOnCompleteListener {

                if (it.isSuccessful) {

                    startActivity(Intent(this, InicialActivity::class.java))
                    Toast.makeText(this, "Bem-Vindo de volta, Gabriel", Toast.LENGTH_LONG).show()
                } else {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidUserException) {
                        textInputLayoutEmail.isErrorEnabled = true
                        textInputLayoutEmail.error = "E-mail não cadastrado"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        textInputLayoutEmail.isErrorEnabled = false
                        textInputLayoutSenha.isErrorEnabled = true
                        textInputLayoutSenha.error = "Senha inválida"
                    }
                }
            }
        }

    }
}