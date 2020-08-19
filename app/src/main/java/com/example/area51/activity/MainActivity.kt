package com.example.area51.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.area51.R
import com.example.area51.model.GetFirebaseService
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import kotlinx.android.synthetic.main.intrologar.*

class MainActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)



        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro1)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro2)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro1)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro3)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intrologincadastro)
                .canGoForward(false)
                .build()
        )

        


    }

    fun cadastrar(view: View){
        startActivity(Intent(this, CadastrarActivity::class.java))
    }

    fun logar(view: View){
        startActivity(Intent(this, LogarActivity::class.java))
    }

  /*  fun logar(view: View) {
        val auth = GetFirebaseService.getFireBaseAuth()
        val user = editTextUsuario.text.toString()
        val password = editTextSenha.text.toString()
        auth?.signOut()

        auth?.signInWithEmailAndPassword(user, password)?.addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Logado", Toast.LENGTH_LONG).show()
                acessarTelaInicial()

            } else {
                try {
                    throw it.exception!!
                }catch (e: FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(this, "usuário ou senha invalido", Toast.LENGTH_LONG).show()
                }catch (e: FirebaseAuthInvalidUserException){
                    Toast.makeText(this, "Não cadastrado", Toast.LENGTH_LONG).show()
                }

            }
        }
    }*/

    fun acessarTelaInicial(){
        startActivity(Intent(this, InicialActivity::class.java ))
    }
}


