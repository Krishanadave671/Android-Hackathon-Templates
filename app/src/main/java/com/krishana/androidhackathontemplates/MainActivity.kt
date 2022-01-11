package com.krishana.androidhackathontemplates

import android.content.ClipData
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.krishana.androidhackathontemplates.fragments.HomeFragment
import com.krishana.androidhackathontemplates.fragments.MessageFragment
import com.krishana.androidhackathontemplates.fragments.SettingsFragment
import com.krishana.androidhackathontemplates.fragments.SignOutFragment

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        var destinationFragment: Fragment?
        // All stuffs related to Navigation drawers
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        // All stuffs related to bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            destinationFragment = when(item.itemId){
                R.id.nav_message -> MessageFragment()
                R.id.nav_settings -> SettingsFragment()
                R.id.nav_home -> HomeFragment()
                else -> null
            }
            destinationFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, it)
                    .commit()
                toolbar.title = getCurrentFragment(item.itemId)
            }
            true
        }



        // Adding toolbar (removing default aqction bar and setting our own actionbar with synced to navController)
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close).syncState()
        toolbar.title = getCurrentFragment(R.id.nav_home)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)


    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        super.onBackPressed()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = supportFragmentManager.beginTransaction()
        val destinationFragment: Fragment = when(item.itemId){
            R.id.nav_signout -> SignOutFragment()
            R.id.nav_settings -> SettingsFragment()
            else -> HomeFragment()
        }

        fragment.replace(R.id.fragment_container_view,destinationFragment).commit()
        toolbar.title = getCurrentFragment(item.itemId)
        if(isThere(item.itemId)){
            bottomNavigationView.selectedItemId = item.itemId
        }
        else {
            bottomNavigationView.selectedItemId = R.id.nav_none
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun getCurrentFragment(fragmentid : Int) : String{
        return when(fragmentid){
            R.id.nav_signout -> "Sign Out"
            R.id.nav_settings -> "Settings"
            R.id.nav_message -> "Message"
            else -> "Home"
        }
    }

    fun isThere(NavID : Int) : Boolean{
        return when(NavID){
            R.id.nav_signout -> false
            else -> true
        }
    }

}