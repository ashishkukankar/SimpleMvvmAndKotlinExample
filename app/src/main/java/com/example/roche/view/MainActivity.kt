package com.example.roche.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.roche.R

private val LOGIN_SCEEN = "LOGINSCREEEN"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
