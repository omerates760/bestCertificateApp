package com.monofire.bestcertificate.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.databinding.FragmentCategoryBinding
import com.monofire.bestcertificate.databinding.FragmentTextPropertiesBinding
import com.monofire.bestcertificate.models.SelectedText

class TextPropertiesFragment : DialogFragment() {
    private var _binding: FragmentTextPropertiesBinding? = null
    private val binding get() = _binding!!
    private var args: String = ""
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTextPropertiesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //args = arguments?.getString("selectedProperty").toString()
        binding.btnDone.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("fromProperties", true)
            bundle.putString("setText", "Lorem ipsum")
            findNavController().navigate(R.id.action_textPropertiesFragment_to_editFragment, bundle)
        }
        binding.textEdit.setOnClickListener {
            binding.customEditText.setText("selamlar.çok başarılı bir durum")
            //binding.customEditText.invalidate()
            //binding.customEditText.requestLayout()
        }
        binding.textSize.setOnClickListener {
            binding.customEditText.textSize=30F
        }

/*      when (whichPage) {
          0,2 -> {
              tempToolbar?.title = "Tavsiye Ekle"
              btn_add_advice.text = "Ekle"
          }
          1 -> {
              tempToolbar?.title = "Tavsiye Güncelle"
              selectedCategory = advice.categoryName
              btn_add_advice.text = "Güncelle"
              add_advice_title.editText!!.setText(advice.title)
              add_advice_description.editText!!.setText(advice.description)
          }
      }
      tempToolbar?.setOnMeItemClickListener {
          when (it.itemId) {
              0 -> dismiss()
              1 -> dismiss()
          }

          true
      }*/


    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

}