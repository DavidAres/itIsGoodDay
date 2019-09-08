package com.example.itisgoodday.settings

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.example.itisgoodday.MainActivity
import com.example.itisgoodday.R
import com.example.itisgoodday.base.BaseFragment
import com.example.itisgoodday.home.HomeFragment
import com.example.itisgoodday.models.Cities
import com.example.itisgoodday.models.City
import com.example.itisgoodday.models.ErrorSettings
import com.example.itisgoodday.models.Settings
import com.example.itisgoodday.settings.interfaces.ISettingsFragment
import com.example.itisgoodday.tools.CitiesSpinner
import com.example.itisgoodday.tools.toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment(), ISettingsFragment {
    override fun getLayout(): Int = R.layout.settings_fragment
    private val settingsViewModel : SettingsViewModel by inject()
    private lateinit var listCities : Cities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        settingsViewModel.stateSettings.observe(this, Observer {
            it?.either(::manageErrorSave, ::redirectToHome)
        })
        settingsViewModel.stateRestore.observe(this, Observer {
            it?.either(::manageErrorRestore, ::loadCurrentSettings)
        })
        settingsViewModel.restoreSettings()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.settings_title)
        inflater?.inflate(R.menu.send_option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.checkToolbar){
            var settings = Settings()
            settings.rainyDay =rainyDay.isChecked
            settings.windyDay = windyDay.isChecked
            settings.maxTemperature = maxTemperatureValue.text.split(" ")[0]
            settings.minTemperature = minTemperatureValue.text.split(" ")[0]
            settings.city = settingsFragmentCitiesSpinner.selectedItem as City
            settingsViewModel.saveSettings(settings)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        maxTemperature.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                maxTemperatureValue.text = "$i Cº"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        minTemperature.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                minTemperatureValue.text = "$i Cº"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        prepareSpinner()
    }

    override fun manageErrorSave(error : ErrorSettings) {
        context?.toast(getString(R.string.error_load_default_settings))
    }

    override fun redirectToHome(settings: Settings) {
        (activity as MainActivity).nav_view.menu.getItem(0).isChecked = true
        (activity as MainActivity).replaceFragment(HomeFragment(), (activity as MainActivity).fragmentContainer.id)
    }

    override fun loadCurrentSettings(settings: Settings) {
        rainyDay.isChecked = settings.rainyDay
        windyDay.isChecked = settings.windyDay
        maxTemperature.progress = settings.maxTemperature.toInt()
        minTemperature.progress = settings.minTemperature.toInt()
        settingsFragmentCitiesSpinner.setSelection((settingsFragmentCitiesSpinner.adapter as ArrayAdapter<City>).getPosition(settings.city))
    }

    override fun manageErrorRestore(error: ErrorSettings) {
        //context?.toast("Nothing to load")
    }

    override fun prepareSpinner() {
        var gson = Gson()
        listCities = gson.fromJson(Settings.mockCities, Cities::class.java)
        var list = ArrayList<City>()
        for (city in listCities.cities.orEmpty()){
            list.add(city)
        }
        val adapter = this!!.activity?.let { CitiesSpinner(it, android.R.layout.simple_spinner_dropdown_item, list) }
        settingsFragmentCitiesSpinner.adapter = adapter
    }

    override fun onDestroy() {
        if (settingsViewModel.stateRestore.hasObservers() && settingsViewModel.stateRestore.hasActiveObservers())
            settingsViewModel.stateRestore.removeObservers(this)
        if (settingsViewModel.stateSettings.hasObservers() && settingsViewModel.stateSettings.hasActiveObservers())
            settingsViewModel.stateSettings.removeObservers(this)
        super.onDestroy()
    }
}