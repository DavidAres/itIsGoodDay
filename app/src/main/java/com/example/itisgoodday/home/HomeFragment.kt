package com.example.itisgoodday.home

import android.arch.lifecycle.Observer
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
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
                homeFragmentTitle.text = getString(R.string.good_day_response) + " " + settings.city!!.name
                animationForResult.setAnimation("1173-sun-burst-weather-icon.json")
                animationForResult.playAnimation()
                animationForResult.loop(true)
            }
            else{
                homeFragmentTitle.text =  getString(R.string.bad_day_response) + " " + settings.city!!.name
                animationForResult.setAnimation("4803-weather-storm.json")
                animationForResult.playAnimation()
                animationForResult.loop(true)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.home_title)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun manageSettingsError(error: ErrorSettings) {
        when (error) {
            ErrorSettings.EMPTY_SETTINGS -> {
                val alert = AlertDialog.Builder(context!!)
                alert.setTitle(getString(R.string.welcome_title))
                alert.setMessage(getString(R.string.welcome_message))
                alert.setPositiveButton(getString(R.string.welcome_configure)
                ) { dialog, _ ->
                    dialog.dismiss()
                    (activity as MainActivity).nav_view.menu.getItem(1).isChecked = true
                    (activity as MainActivity).replaceFragment(SettingsFragment(), (activity as MainActivity).fragmentContainer.id)
                }
                alert.setCancelable(false)
                alert.show()
            }
            ErrorSettings.LOAD_ERROR_SETTINGS -> {
                context?.toast(getString(R.string.error_load_settings))
                (activity as MainActivity).nav_view.menu.getItem(1).isChecked = true
                (activity as MainActivity).replaceFragment(SettingsFragment(), (activity as MainActivity).fragmentContainer.id)
            }
        }
    }

    override fun manageWeatherError(error: ErrorWeather) {
        when (error){
            ErrorWeather.LOAD_ERROR -> context?.toast(getString(R.string.error_load_weather), Toast.LENGTH_LONG)
        }
    }

    override fun saveSettingsAndLoad(settings: Settings) {
        this.settings = settings
        homeViewModel.getWeatherInformation( settings.city!!.lat, settings.city!!.long)
    }

    override fun compareResults(weather: Weather) {
        homeViewModel.calculateDay(weather)
    }

    override fun onDestroy() {
        if (homeViewModel.stateDay.hasObservers() && homeViewModel.stateDay.hasActiveObservers())
            homeViewModel.stateDay.removeObservers(this)
        if (homeViewModel.stateSettings.hasObservers() && homeViewModel.stateSettings.hasActiveObservers())
            homeViewModel.stateSettings.removeObservers(this)
        if (homeViewModel.stateWeather.hasObservers() && homeViewModel.stateWeather.hasActiveObservers())
            homeViewModel.stateWeather.removeObservers(this)
        super.onDestroy()
    }

}