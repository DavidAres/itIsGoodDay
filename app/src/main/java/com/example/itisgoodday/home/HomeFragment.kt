package com.example.itisgoodday.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.example.itisgoodday.MainActivity
import com.example.itisgoodday.R
import com.example.itisgoodday.base.BaseFragment
import com.example.itisgoodday.models.ErrorSettings
import com.example.itisgoodday.models.Settings
import com.example.itisgoodday.models.ErrorWeather
import com.example.itisgoodday.models.Weather
import com.example.itisgoodday.home.interfaces.IHomeFragment
import com.example.itisgoodday.settings.SettingsFragment
import com.example.itisgoodday.tools.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), IHomeFragment {
    val homeViewModel : HomeViewModel by inject()
    private lateinit var settings : Settings

    override fun getLayout(): Int = R.layout.home_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //homeViewModel.restoreSetting()
        homeViewModel.stateSettings.observe(this, Observer {
            it?.either(::manageSettingsError, ::saveSettingsAndLoad)
        })
        homeViewModel.stateWeather.observe(this, Observer {
            it?.either(::manageWeatherError, ::compareResults)
        })
        homeViewModel.getWeatherInformation( "37.8267","-122.4233")
    }

    override fun manageSettingsError(error: ErrorSettings) {
        when (error) {
            ErrorSettings.EMPTY_SETTINGS -> {
                context?.toast("You do not have settings yet, please enter how is a good day for you", Toast.LENGTH_LONG)
                (activity as MainActivity).replaceFragment(SettingsFragment(), (activity as MainActivity).fragmentContainer.id)
            }
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
        homeViewModel.getWeatherInformation( "37.8267","-122.4233")
    }

    override fun compareResults(weather: Weather) {
        context?.toast(weather.daily.data[0].toString())
    }

}