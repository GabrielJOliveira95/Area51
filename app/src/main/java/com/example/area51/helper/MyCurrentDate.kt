package com.example.area51.helper

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat

class MyCurrentDate {

    companion object{

        fun dataAtual(): String{
            val dataMil = System.currentTimeMillis()
            @SuppressLint("SimpleDateFormat")
            val sdf = SimpleDateFormat("dd:MM:YYYY")
            val data = sdf.format(dataMil)
            Log.i("DATA VALOR", "$data")


            val mesAno = data.split(":")
            val mes = mesAno[1]
            val ano = mesAno[2]

            return "$mes$ano"
        }


    }
}