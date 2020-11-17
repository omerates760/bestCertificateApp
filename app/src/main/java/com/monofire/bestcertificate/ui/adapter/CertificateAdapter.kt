package com.monofire.bestcertificate.ui.adapter

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.databinding.ItemCategoryBinding
import com.monofire.bestcertificate.models.CertificateItem
import java.io.InputStream

class CertificateAdapter(
    private val list: MutableList<CertificateItem>,
    private val categoryId: String
) :
    RecyclerView.Adapter<CertificateAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding, categoryId)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int = list.size

    class MyViewHolder(
        private val rowBinding: ItemCategoryBinding,
        private val categoryId: String
    ) :
        RecyclerView.ViewHolder(rowBinding.root) {
        fun bind(certificate: CertificateItem) {
            val xx: InputStream =
                rowBinding.root.context.assets.open(certificate.certificateImage)
            /* val stream =
                 Uri.parse("android.resource://com.monofire.bestcertificate/raw/exam.jpg")*/
            val aa = BitmapFactory.decodeStream(xx)
            Glide.with(rowBinding.root.context)
                .load(aa)
                .into(rowBinding.certificateImage)

            when (certificate.isLocked) {
                true -> {
                    rowBinding.categoryLocked.visibility = View.VISIBLE
                    rowBinding.categoryLockedIcon.visibility = View.VISIBLE
                    rowBinding.certificateImage.setOnClickListener {
                        Log.e("result", "Bu sertifika ücretlidir.")
                    }
                }
                false -> {
                    rowBinding.categoryLocked.visibility = View.INVISIBLE
                    rowBinding.categoryLockedIcon.visibility = View.INVISIBLE
                    rowBinding.certificateImage.setOnClickListener {
                        val gson = Gson()
                        val json = gson.toJson(certificate)
                        val bundle = Bundle()
                        bundle.putString("categoryId", categoryId)
                        bundle.putString("certificateProperties", json)
                        Navigation.findNavController(rowBinding.root)
                            .navigate(R.id.action_certificateFragment_to_editFragment, bundle)
                    }
                }
            }

        }

    }
}