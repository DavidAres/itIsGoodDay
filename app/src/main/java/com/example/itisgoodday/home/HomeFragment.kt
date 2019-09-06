package com.example.itisgoodday.home

import android.os.Bundle
import com.example.itisgoodday.base.BaseFragment
import kotlinx.coroutines.currentScope
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val homeViewModel : HomeViewModel by viewModel()

    override fun getLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel<HomeViewModel>()
    }

}