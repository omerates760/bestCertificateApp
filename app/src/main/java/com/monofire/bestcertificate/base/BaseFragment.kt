package com.monofire.bestcertificate.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment:Fragment() {
    private var fragmentContent: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentContent=inflater.inflate(getLayoutID(), container, false)
        return fragmentContent
    }

    abstract fun getLayoutID(): Int
}