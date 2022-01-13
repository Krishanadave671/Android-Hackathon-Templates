package com.krishana.androidhackathontemplates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SignOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_out)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}