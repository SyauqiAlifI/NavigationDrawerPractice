package com.alif.navigationdrawerstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.alif.navigationdrawerstudy.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  Set ActionBar with Toolbar
//        setSupportActionBar(binding.includeAppbarMain.mainToolbar)

        //  FAB onClick will show SnackBar
        binding.includeAppbarMain.fabAppbarMain.setOnClickListener {
            Snackbar.make(it, "Hello There, Nice to Meet Ya", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
        //  Variable setups
        drawerLayout = binding.parentDrawerLayout
        val navDrawer: NavigationView = binding.navDrawer
        //  Navigation Controller
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        //  App bar Config
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navHomeFragment,
                R.id.navCartFragment,
                R.id.navMeFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navDrawer.setupWithNavController(navController)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        NavigationUI.setupWithNavController(navDrawer, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}