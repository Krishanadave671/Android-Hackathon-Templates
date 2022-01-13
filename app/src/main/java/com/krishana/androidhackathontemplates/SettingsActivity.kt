package com.krishana.androidhackathontemplates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
}