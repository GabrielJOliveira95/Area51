package com.example.area51.model

import com.example.area51.helper.GetFirebaseService
import com.google.firebase.database.Exclude

class Usuario(@get:Exclude var id: String? = null, var nome: String? = null, var email: String? = null, @get: Exclude var senha: String? = null) {


    fun salvarDados(){
        val databaseReference = GetFirebaseService.getDataBaseReference()
        val id = this.id.toString().replace("\n", "")
        databaseReference?.child("Usuarios")?.child(id)?.setValue(this)
    }



}