package com.example.itisgoodday.settings.interfaces

import com.example.itisgoodday.models.Settings

interface ISettingsViewModel {
    fun saveSettings(settings: Settings)
    fun restoreSettings()
}