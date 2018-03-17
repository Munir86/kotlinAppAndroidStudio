package com.munir.us.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        buGetAge.setOnClickListener({
            //button is clicked
            val yearOfBirth:Int=etUserOfBirth.text.toString().toInt()
            val currentYear=Calendar.getInstance().get(Calendar.YEAR)
            val age=currentYear-yearOfBirth
            tvShowAge.text="Your Age is "+ age + " Year"


        })
        */

    }

        fun buClickEvent(view:View) {
        // button is clicked
        val yearOfBirth:Int=etUserOfBirth.text.toString().toInt()
        val currentYear=Calendar.getInstance().get(Calendar.YEAR)
        val age= currentYear-yearOfBirth
        tvShowAge.text="Your Age is "+ age +"Years"

    }
}
