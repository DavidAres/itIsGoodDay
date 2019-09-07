package com.example.itisgoodday

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.itisgoodday.base.BaseActivity
import com.example.itisgoodday.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayout() = R.layout.activity_main

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val ft = supportFragmentManager.beginTransaction()
                val homeFragment = HomeFragment()
                ft.replace(fragmentContainer.id, homeFragment)
                ft.commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val ft = supportFragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        ft.replace(fragmentContainer.id, homeFragment)
        ft.commit()
    }

    companion object {
        const val SAVE_SETTINGS = "Settings"
    }
}
