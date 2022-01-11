package com.krishana.androidhackathontemplates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.krishana.androidhackathontemplates.fragments.HomeFragment
import com.krishana.androidhackathontemplates.fragments.SettingsFragment
import com.krishana.androidhackathontemplates.fragments.SignOutFragment

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // All stuffs related to Navigations
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)
        supportActionBar?.title = getCurrentFragment(R.id.nav_home)


    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        super.onBackPressed()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = supportFragmentManager.beginTransaction()
        val destinationFragment: Fragment = when(item.itemId){
            R.id.nav_signout -> SignOutFragment()
            R.id.nav_settings -> SettingsFragment()
            else -> HomeFragment()
        }

        fragment.replace(R.id.fragment_container_view,destinationFragment).commit()
        supportActionBar?.title = getCurrentFragment(item.itemId)
        return true
    }

    fun getCurrentFragment(fragmentid : Int) : String{
        return when(fragmentid){
            R.id.nav_signout -> "Sign Out"
            R.id.nav_settings -> "Settings"
            else -> "Home"
        }
    }

}