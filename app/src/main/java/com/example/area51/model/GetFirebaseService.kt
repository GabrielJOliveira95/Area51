package com.example.area51.model

import com.google.firebase.auth.FirebaseAuth

class GetFirebaseService {

    companion object {
        var auth: FirebaseAuth? = null


        fun getFireBaseAuth(): FirebaseAuth? {
            if (auth == null) {
                auth = FirebaseAuth.getInstance()
            }
            return auth
        }
    }
}