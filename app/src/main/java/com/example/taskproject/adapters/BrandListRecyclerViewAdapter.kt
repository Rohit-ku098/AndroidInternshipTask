package com.example.taskproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.model.Brand

class BrandListRecyclerViewAdapter(val brands: List<Brand>): RecyclerView.Adapter<BrandListRecyclerViewAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var mListener: ItemClickListener

    fun setOnItemClickListener(listener: ItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun getItemCount(): Int = brands.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.brandName.text = brands[position].name
        holder.brandLink.text = brands[position].link
        holder.brandImage.setImageResource(brands[position].image)
    }

    class ViewHolder(itemView: View, listener: ItemClickListener): RecyclerView.ViewHolder(itemView) {
        val brandName = itemView.findViewById<TextView>(R.id.brandName)
        val brandLink = itemView.findViewById<TextView>(R.id.brandLink)
        val brandImage = itemView.findViewById<ImageView>(R.id.brandImage)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}