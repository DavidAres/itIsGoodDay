package com.example.itisgoodday.settings.interfaces

import com.example.itisgoodday.models.Cities
import com.example.itisgoodday.models.ErrorSettings
import com.example.itisgoodday.models.Settings

interface ISettingsFragment {
    fun redirectToHome( settings: Settings)
    fun manageErrorSave(error: ErrorSettings)
    fun loadCurrentSettings(settings: Settings)
    fun manageErrorRestore(error : ErrorSettings)
    fun prepareSpinner()
}