package com.example.area51.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.area51.R
import com.example.area51.adapter.AdapterFotos
import com.example.area51.model.GetFirebaseService
import kotlinx.android.synthetic.main.content_main.*


class InicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Galeria"

        configurarCalendario()
        recyclerViewConfig()

        /*findViewById<FloatingActionButton>(R.id.menu).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }


    fun irUpLoadFotos(view:View){
        startActivity(Intent(this@InicialActivity, UpLoadFotoActivity::class.java))
    }

    fun configurarCalendario(){
        val meses = arrayOf("Janeiro", "Fevereiro", "Março","Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro")
        calendarView.setTitleMonths(meses)
    }

    fun recyclerViewConfig(){
        val adapter = AdapterFotos()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.hasFixedSize()
    }

    fun sair() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Sair")
        alertDialog.setMessage("Tem certeza de que deseja sair?")
        alertDialog.setNegativeButton("Não", null)
        alertDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24)
        alertDialog.setPositiveButton("Sim") { _: DialogInterface, _: Int ->

            val fireBaseAuth = GetFirebaseService.getFireBaseAuth()
            fireBaseAuth?.signOut()
            finish()

        }
        alertDialog.create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        sair()
    }


}