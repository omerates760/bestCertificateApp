package com.monofire.bestcertificate.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.SampleView
import com.monofire.bestcertificate.databinding.FragmentEditBinding
import com.monofire.bestcertificate.db.LocalDatabase
import com.monofire.bestcertificate.models.CertificateItem
import com.monofire.bestcertificate.models.SelectedText
import com.monofire.bestcertificate.save.SharedText
import com.monofire.bestcertificate.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var localDatabase: LocalDatabase
    private lateinit var adapters: CategoryAdapter
    lateinit var certificateProperties: CertificateItem
    lateinit var sampleView: SampleView
    private val gson = Gson()


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

        binding.btnText.setOnClickListener {
            SharedText.getTextProperties(requireContext())
            val bundle = Bundle()
            bundle.putString("selectedProperty", "text")
            findNavController().navigate(R.id.action_editFragment_to_textPropertiesFragment, bundle)

        }
        if (arguments?.getBoolean("fromProperties") == true) {
            sampleView = SampleView(requireContext())
            val textProperty = SelectedText(
                arguments?.getString("setText").toString(), SharedText.getTextProperties(
                    requireContext()
                )!!.layoutTranslate
            )
            sampleView.changeCustomText(textProperty)
            binding.container.addView(sampleView)
        } else {
            certificateProperties = gson.fromJson(
                arguments?.getString("certificateProperties", ""),
                CertificateItem::class.java
            )
            sampleView = SampleView(requireContext(), certificateProperties)
            binding.container.addView(sampleView)


        }

    }
}