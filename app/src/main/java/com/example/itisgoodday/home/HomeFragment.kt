package com.example.itisgoodday.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.example.itisgoodday.base.BaseFragment
import com.example.itisgoodday.data.ErrorSettings
import com.example.itisgoodday.data.Settings
import com.example.itisgoodday.data.ErrorWeather
import com.example.itisgoodday.data.Weather
import com.example.itisgoodday.home.interfaces.IHomeFragment
import com.example.itisgoodday.tools.toast
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), IHomeFragment {
    private val homeViewModel : HomeViewModel by currentScope.viewModel(this)
    lateinit var settings : Settings

    override fun getLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.restoreSetting()
        homeViewModel.stateSettings.observe(this, Observer {
            it?.either(::manageSettingsError, ::saveSettingsAndLoad)
        })
        homeViewModel.stateWeather.observe(this, Observer {
            it?.either(::manageWeatherError, ::compareResults)
        })
    }

    override fun manageSettingsError(error: ErrorSettings) {
        when (error) {
            ErrorSettings.EMPTY_SETTINGS -> context?.toast("You do not have settings yet, please enter how is a good day for you", Toast.LENGTH_LONG)
            ErrorSettings.LOAD_ERROR_SETTINGS -> context?.toast("Something was wrong with your settings!, we should add again...")
        }
    }

    override fun manageWeatherError(error: ErrorWeather) {
        when (error){
            ErrorWeather.LOAD_ERROR -> context?.toast("We cannot load the weather for today, sorry", Toast.LENGTH_LONG)
        }
    }

    override fun saveSettingsAndLoad(settings: Settings) {
        this.settings = settings
        homeViewModel.getWeatherInformation(0,0)
    }

    override fun compareResults(weather: Weather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}