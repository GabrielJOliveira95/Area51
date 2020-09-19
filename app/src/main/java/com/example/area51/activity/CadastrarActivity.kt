package com.example.area51.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.area51.R
import com.example.area51.model.GetFirebaseService
import com.example.area51.model.MyBase64
import com.example.area51.model.Usuario
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_cadastrar.*

class CadastrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        title = getString(R.string.cadastrar)

        btn_confirmar_cadastro.setOnClickListener {
            cadastrar()
        }
    }

    fun validarCamposVazios(): Boolean {

        val campoValidado: Boolean

        when {
            txt_nome_cadastro.text?.toString()?.isEmpty()!! -> {
                input_email_cadastro.isErrorEnabled = false
                input_senha_cadastro.isErrorEnabled = false

                input_nome_cadastro.isErrorEnabled = true
                input_nome_cadastro.error = "Digite o seu nome"

                campoValidado = false
            }
            txt_email_cadastro.text?.toString()?.isEmpty()!! -> {
                input_email_cadastro.isErrorEnabled = true
                input_email_cadastro.error = "Digite o seu email"

                input_nome_cadastro.isErrorEnabled = false
                campoValidado = false
            }
            txt_senha_cadastro.text?.toString()?.isEmpty()!! -> {
                input_senha_cadastro.isErrorEnabled = true
                input_senha_cadastro.error = "Digite a sua senha"

                input_nome_cadastro.isErrorEnabled = false
                input_email_cadastro.isErrorEnabled = false

                campoValidado = false
            }
            else -> {

                input_nome_cadastro.isErrorEnabled = false
                input_email_cadastro.isErrorEnabled = false
                input_senha_cadastro.isErrorEnabled = false

                campoValidado = true

            }
        }

        return campoValidado
    }


    fun cadastrar() {

        val auth = GetFirebaseService.getFireBaseAuth()
        val usuario = Usuario()

        val nome = txt_nome_cadastro.text.toString()
        val email = txt_email_cadastro.text.toString()
        val senha = txt_senha_cadastro.text.toString()

        usuario.nome = nome
        usuario.email = email
        usuario.senha = senha

        if (validarCamposVazios()) {
            auth?.createUserWithEmailAndPassword(email, senha)?.addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show()
                    val idCodificado = MyBase64.codificar(usuario.email!!)
                    usuario.id = idCodificado
                    usuario.salvarDados()
                    startActivity(Intent(this@CadastrarActivity, InicialActivity::class.java))
                } else {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthUserCollisionException) {
                        input_email_cadastro.isErrorEnabled = true
                        input_email_cadastro.error = "Usuário já cadastrado"

                    } catch (e: FirebaseAuthWeakPasswordException) {
                        input_email_cadastro.isErrorEnabled = false

                        input_senha_cadastro.isErrorEnabled = true
                        input_senha_cadastro.error = "Digite um senha com no minímo 6 digitos"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        input_senha_cadastro.isErrorEnabled = false
                        input_email_cadastro.isErrorEnabled = true

                        input_email_cadastro.error = "Digite um e-mail válido"
                    }
                }

            }
        }

    }
}