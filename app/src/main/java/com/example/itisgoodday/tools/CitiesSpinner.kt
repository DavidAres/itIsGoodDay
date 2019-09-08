package com.example.itisgoodday.tools

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.itisgoodday.R
import com.example.itisgoodday.models.City


class CitiesSpinner(private val context: Activity, resource: Int, data2: ArrayList<City>) :
    ArrayAdapter<City>(context, resource, data2) {
    private var data: ArrayList<City>? = null

    init {
        this.data = data2
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row == null) {
            //inflate your customlayout for the textview
            val inflater = context.layoutInflater
            row = inflater.inflate(R.layout.simple_spinner, parent, false)
        }
        //put the data in it
        val item = data!![position]
        if (item != null) {
            val text1 = row!!.findViewById(R.id.txt_row_simple_spin) as TextView
            text1.text = item.name
            if(position == 0){
                text1.setTextColor(Color.GRAY)
            }else{
                text1.setTextColor(Color.BLACK)
            }
        }

        return row!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // I created a dynamic TextView here, but you can reference your own custom layout for each spinner item
        val label = super.getView(position, convertView, parent) as TextView
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.text = data!![position].name

        // And finally return your dynamic (or custom) view for each spinner item
        return label
    }

}