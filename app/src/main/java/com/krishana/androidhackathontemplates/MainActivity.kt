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
import com.google.android.material.navigation.NavigationView
import com.krishana.androidhackathontemplates.fragments.HomeFragment
import com.krishana.androidhackathontemplates.fragments.MessageFragment
import com.krishana.androidhackathontemplates.fragments.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
  private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var destinationFragment: Fragment
        // All stuffs related to Navigation drawers
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        // All stuffs related to bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            destinationFragment = when(item.itemId){
                R.id.nav_message -> MessageFragment()
                R.id.nav_favorites -> FavouritesFragment()
                else -> HomeFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, destinationFragment)
                .commit()
//            toolbar.title = getCurrentFragment(item.itemId)

            true
        }



        // Adding toolbar (removing default aqction bar and setting our own actionbar with synced to navController)
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        ActionBarDrawerToggle(this,drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).syncState()
        toolbar.title = getCurrentFragment(R.id.nav_home)
        toolbar.visibility = View.INVISIBLE
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
        val destinationClass  = when(item.itemId){
            R.id.nav_signout -> SignOutActivity::class.java
            R.id.nav_settings -> SettingsActivity::class.java
            R.id.nav_log_in -> LogInActivity::class.java
            else -> MainActivity::class.java
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        if(destinationClass != MainActivity::class.java){
            val intent = Intent(this,destinationClass)
            startActivity(intent)
        }
        return true
    }

    fun getCurrentFragment(fragmentid : Int) : String{
        return when(fragmentid){
            R.id.nav_favorites -> "Favourites"
            R.id.nav_message -> "Message"
            else -> "Home"
        }
    }




}