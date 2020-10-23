package com.monofire.bestcertificate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.SampleView
import com.monofire.bestcertificate.base.BaseFragment

class HomeFragment : BaseFragment() {
    lateinit var mCustomView:SampleView
    override fun getLayoutID(): Int = R.layout.fragment_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //mCustomView = view.findViewById(R.id.customView)

    }
}