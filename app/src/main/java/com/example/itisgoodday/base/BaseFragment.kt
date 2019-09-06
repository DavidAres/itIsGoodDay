package com.example.itisgoodday.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayout()
    }

    abstract fun getLayout(): Int

    inline fun <reified T : ViewModel> getViewModel(): T =
        ViewModelProviders.of(this, getViewModelFactory()).get(T::class.java)

    abstract fun getViewModelFactory(): ViewModelProvider.NewInstanceFactory
}