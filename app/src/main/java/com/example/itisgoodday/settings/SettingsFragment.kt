package com.example.itisgoodday.settings

import com.example.itisgoodday.R
import com.example.itisgoodday.base.BaseFragment
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.settings_fragment
    val settingsViewModel : SettingsViewModel by inject()
}