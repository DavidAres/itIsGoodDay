package com.example.itisgoodday.settings

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.itisgoodday.models.ErrorSettings
import com.example.itisgoodday.models.Settings
import com.example.itisgoodday.settings.interfaces.ISettingsViewModel
import com.example.itisgoodday.tools.Constans.Companion.SAVE_SETTINGS
import com.example.itisgoodday.tools.Either
import com.example.itisgoodday.tools.PreferencesManager
import java.lang.Exception
import com.google.gson.Gson



class SettingsViewModel(var context: Context) : ViewModel(), ISettingsViewModel{
    private var saveSettingsLiveData = MutableLiveData<Either<ErrorSettings, Settings>>()
    val stateSettings : LiveData<Either<ErrorSettings, Settings>>
        get() = saveSettingsLiveData
    private var restoreSettingsLiveData = MutableLiveData<Either<ErrorSettings, Settings>>()
    val stateRestore : LiveData<Either<ErrorSettings, Settings>>
        get() = restoreSettingsLiveData

    override fun saveSettings(settings: Settings) {
        try{
            val gson = Gson()
            val json = gson.toJson(settings)
            var result = PreferencesManager.save(context, SAVE_SETTINGS, json)
            when (result){
                PreferencesManager.Status.SUCCESS ->  saveSettingsLiveData.postValue(Either.Success(settings))
                PreferencesManager.Status.ERROR -> saveSettingsLiveData.postValue(Either.Error(ErrorSettings.SAVE_ERROR))
            }
        }catch (e : Exception){
            saveSettingsLiveData.postValue(Either.Error(ErrorSettings.SAVE_ERROR))
        }
    }

    override fun restoreSettings() {
        var settings = PreferencesManager.restoreString(context, SAVE_SETTINGS )
        val gson = Gson()
        var saveSettings = gson.fromJson(settings, Settings::class.java)
        restoreSettingsLiveData.postValue(
            if (settings.isNullOrEmpty())
                Either.Error(ErrorSettings.EMPTY_SETTINGS)
            else {
                Either.Success(saveSettings)
            })
    }



}