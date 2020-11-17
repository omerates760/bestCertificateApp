package com.monofire.bestcertificate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.monofire.bestcertificate.databinding.FragmentCategoryBinding
import com.monofire.bestcertificate.databinding.FragmentCertificateBinding
import com.monofire.bestcertificate.db.LocalDatabase
import com.monofire.bestcertificate.models.CertificateItem
import com.monofire.bestcertificate.ui.adapter.CategoryAdapter
import com.monofire.bestcertificate.ui.adapter.CertificateAdapter

class CertificateFragment : Fragment() {
    private var _binding: FragmentCertificateBinding? = null
    private lateinit var adapters: CertificateAdapter
    private val binding get() = _binding!!
    private lateinit var localDatabase: LocalDatabase
    var categoryId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCertificateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryId = arguments?.getString("categoryId", "boş").toString()
        loadCertificates()
    }

    private fun loadCertificates() {
         val categoryListItems = mutableListOf<CertificateItem>()
        localDatabase = LocalDatabase(requireContext())
        localDatabase.readFile()
        localDatabase.readSelectedCertificateList(categoryId).forEach { items ->
            categoryListItems.addAll(items.certificateList)
        }
        adapters = CertificateAdapter(categoryListItems, categoryId)

        binding.rcCertificate.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
        }

    }
}