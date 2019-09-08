package com.example.itisgoodday.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
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
import kotlinx.android.synthetic.main.home_fragment.*
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
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        homeViewModel.restoreSetting()
        homeViewModel.stateSettings.observe(this, Observer {
            it?.either(::manageSettingsError, ::saveSettingsAndLoad)
        })
        homeViewModel.stateWeather.observe(this, Observer {
            it?.either(::manageWeatherError, ::compareResults)
        })
        homeViewModel.stateDay.observe(this, Observer {
            if (it!!) {
                homeFragmentTitle.text = "Is a good day"
                animationForResult.setAnimation("1173-sun-burst-weather-icon.json")
                animationForResult.playAnimation()
                animationForResult.loop(true)
            }
            else{
                homeFragmentTitle.text = "Is a bad day"
                animationForResult.setAnimation("502-cloud.json")
                animationForResult.playAnimation()
                animationForResult.loop(true)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        (activity as MainActivity).supportActionBar?.title = "It is a good day"
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun manageSettingsError(error: ErrorSettings) {
        when (error) {
            ErrorSettings.EMPTY_SETTINGS -> {
                context?.toast("You do not have settings yet, please enter how is a good day for you", Toast.LENGTH_LONG)
                (activity as MainActivity).nav_view.menu.getItem(1).isChecked = true
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
        homeViewModel.getWeatherInformation( settings.city!!.lat, settings.city!!.long)
    }

    override fun compareResults(weather: Weather) {
        homeViewModel.calculateDay(weather)
    }

}