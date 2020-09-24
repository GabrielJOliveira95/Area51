package com.example.area51.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class GetFirebaseService {

    companion object {
        var auth: FirebaseAuth? = null
        var dataBase: DatabaseReference? = null
        var storage: StorageReference? = null

        fun getDataBaseReference(): DatabaseReference?{
            if (dataBase == null){
                dataBase = FirebaseDatabase.getInstance().reference
            }
            return dataBase
        }

        fun getFireBaseAuth(): FirebaseAuth? {
            if (auth == null) {
                auth = FirebaseAuth.getInstance()
            }
            return auth
        }

        fun getStorageReference(): StorageReference?{
            if (storage == null) {
                storage = Firebase.storage.reference
            }

            return storage
        }
    }
}