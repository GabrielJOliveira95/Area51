package com.example.area51.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.area51.R
import com.example.area51.helper.GetFirebaseService
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

class MainActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)


        manterLogado()

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

    fun cadastrar(view: View) {
        startActivity(Intent(this, CadastrarActivity::class.java))
    }

    fun logar(view: View) {
        startActivity(Intent(this, LogarActivity::class.java))
    }

    fun manterLogado() {
        val auth  = GetFirebaseService.getFireBaseAuth()
        val user = auth?.currentUser
        if (user != null) {
            startActivity(Intent(this, InicialActivity::class.java))
        }
    }

}


