package com.example.roche.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roche.R

private val LOGIN_SCEEN = "LOGINSCREEEN"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
    }


    private fun addFragment() {
       supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, LoginScreenFragment(),
                ""
            )
           .addToBackStack(null)
           .commit()
    }
}
