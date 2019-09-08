package com.example.itisgoodday.tools

import android.content.Context
import android.widget.Toast

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, duration).show()
}

fun Float.toCelsius() : Float{
    this - 32
    return this * 5 / 9
}