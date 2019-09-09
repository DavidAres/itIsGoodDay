package com.example.itisgoodday

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.TextView
import com.example.itisgoodday.base.BaseActivity
import com.example.itisgoodday.home.HomeFragment
import com.example.itisgoodday.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayout() = R.layout.activity_main

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment( HomeFragment(), fragmentContainer.id)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifications -> {
                replaceFragment( SettingsFragment(), fragmentContainer.id)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val ft = supportFragmentManager.beginTransaction()
        ft.addToBackStack(null)
        ft.add( fragmentContainer.id,HomeFragment())
        ft.commit()
    }

    override fun onBackPressed() {
        var currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (currentFragment is HomeFragment) {
            this.finishAffinity()
        }
        super.onBackPressed()
    }
}
