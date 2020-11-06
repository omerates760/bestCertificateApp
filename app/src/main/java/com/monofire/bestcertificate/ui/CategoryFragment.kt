package com.monofire.bestcertificate.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.monofire.bestcertificate.databinding.FragmentCategoryBinding
import com.monofire.bestcertificate.db.LocalDatabase
import com.monofire.bestcertificate.ui.adapter.CategoryAdapter

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var localDatabase: LocalDatabase
    private lateinit var adapters: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCertificate()

    }
    private fun loadCertificate() {
        localDatabase = LocalDatabase(requireContext())
        val liste = localDatabase.readFile()
        adapters = CategoryAdapter(liste)

        binding.recyclerviewCategory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}