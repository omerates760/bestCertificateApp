package com.monofire.bestcertificate.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.databinding.ItemCategorySelectBinding
import com.monofire.bestcertificate.models.Certificate

class CategoryAdapter(private val list: MutableList<Certificate>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemCategorySelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int = list.size

    class MyViewHolder(private val rowBinding: ItemCategorySelectBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        fun bind(certificate: Certificate) {
            rowBinding.categoryName.text = certificate.certificateCategory
            rowBinding.categoryName.setOnClickListener {
                val bundle=Bundle()
                bundle.putString("categoryId",certificate.categoryId)
                findNavController(rowBinding.root).navigate(R.id.action_categoryFragment_to_certificateFragment,bundle)
            }
        }

    }
}

