package com.example.area51.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.area51.R

class AdapterFotos : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewfotos, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder is MyViewHolder){

        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var foto = itemView.findViewById<ImageView>(R.id.fotoRecyclerView)
    }
}