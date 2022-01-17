package com.krishana.androidhackathontemplates


import android.content.Intent
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.krishana.androidhackathontemplates.fragments.HomeFragment
import com.krishana.androidhackathontemplates.fragments.MessageFragment
import com.krishana.androidhackathontemplates.fragments.*

class MainActivity : AppCompatActivity(){

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            val destinationActivity  = when(it.itemId){
                R.id.nav_items -> RecyclerViewActivity::class.java

                else -> MainActivity::class.java
            }
            if(it.itemId != R.id.nav_home){
                startActivity(Intent(this,destinationActivity))
            }
            true
        }

        val addButton = findViewById<FloatingActionButton>(R.id.add_items)
        addButton.setOnClickListener { startActivity(Intent(this,FireBaseActivity::class.java)) }

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener{
            val destinationActivity = when(it.itemId) {
                R.id.nav_signout -> SignOutActivity::class.java
                R.id.nav_log_in -> LogInActivity::class.java
                R.id.nav_settings -> SettingsActivity::class.java
                else -> MainActivity::class.java
            }
            startActivity(Intent(this,destinationActivity))
            true
        }


    }

}