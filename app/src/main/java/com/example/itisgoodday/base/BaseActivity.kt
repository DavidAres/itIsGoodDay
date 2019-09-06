package com.example.itisgoodday.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

//Create a base activity allow us to avoid the same declaration for each activity that we create
abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    abstract fun getLayout(): Int

    fun showErrorMessage(errorMessage: String){
        this.showErrorMessage(errorMessage)
    }
}