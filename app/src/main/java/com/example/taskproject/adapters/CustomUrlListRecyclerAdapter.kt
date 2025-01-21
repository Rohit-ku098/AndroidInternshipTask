package com.example.taskproject.adapters

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskproject.R
import com.example.taskproject.databinding.ItemCustomUrlBinding
import com.example.taskproject.model.CustomUrl

class CustomUrlListRecyclerAdapter(private val customUrls: List<CustomUrl>, private val context: Context): RecyclerView.Adapter<CustomUrlListRecyclerAdapter.ViewHolder>() {
    val tag = "ProjectDebug"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(tag, "onCreateViewHolder: ")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_custom_url, parent, false))
    }
    init {
        Log.d(tag, "init: ${customUrls}")
    }

    override fun getItemCount(): Int = customUrls.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.label.text = customUrls[position].label
        holder.price.text = customUrls[position].price
        Glide
            .with(context)
            .load(customUrls[position].imageUrl)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .fallback(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(holder.icon)
        Log.d(tag, "onBindViewHolder: ")
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val label = view.findViewById<TextView>(R.id.name)
        val price = view.findViewById<TextView>(R.id.price)
        val icon = view.findViewById<ImageView>(R.id.icon)
    }

}