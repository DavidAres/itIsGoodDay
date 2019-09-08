package com.example.itisgoodday.settings

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import com.example.itisgoodday.MainActivity
import com.example.itisgoodday.R
import com.example.itisgoodday.base.BaseFragment
import com.example.itisgoodday.home.HomeFragment
import com.example.itisgoodday.home.HomeViewModel
import com.example.itisgoodday.models.ErrorSettings
import com.example.itisgoodday.models.Settings
import com.example.itisgoodday.settings.interfaces.ISettingsFragment
import com.example.itisgoodday.tools.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment(), ISettingsFragment {
    override fun getLayout(): Int = R.layout.settings_fragment
    private val settingsViewModel : SettingsViewModel by inject()

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
        (activity as MainActivity).supportActionBar?.title = "Settings"
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
    }

    override fun manageErrorSave(error : ErrorSettings) {
        context?.toast("Something was wrong saving the settings ,please try again")
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
    }

    override fun manageErrorRestore(error: ErrorSettings) {
        //context?.toast("Nothing to load")
    }
}