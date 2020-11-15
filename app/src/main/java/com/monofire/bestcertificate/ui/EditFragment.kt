package com.monofire.bestcertificate.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.databinding.FragmentCategoryBinding
import com.monofire.bestcertificate.databinding.FragmentEditBinding
import com.monofire.bestcertificate.db.LocalDatabase
import com.monofire.bestcertificate.ui.adapter.CategoryAdapter

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var localDatabase: LocalDatabase
    private lateinit var adapters: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("result1", "" + arguments?.getString("certificateId","boş"))
    }
}