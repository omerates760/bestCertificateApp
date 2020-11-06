package com.monofire.bestcertificate.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.databinding.ItemCategoryBinding
import com.monofire.bestcertificate.models.Certificate

class CategoryAdapter(private val list: MutableList<Certificate>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int = list.size

    class MyViewHolder(private val rowBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        fun bind(certificate: Certificate) {
            rowBinding.certificateImage.setImageResource(R.drawable.thumb)

            rowBinding.categoryLocked.visibility = View.VISIBLE
        }

    }
}

