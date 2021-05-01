package com.reminder.reminderapp.ui.activity

import android.R.attr.category
import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.reminder.reminderapp.R
import com.reminder.reminderapp.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private var content: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)!!.setDisplayShowTitleEnabled(false)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_riwayat,
                R.id.nav_home,
                R.id.nav_report
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationMain.setupWithNavController(navController)

        val navigation = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navigation, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.bottomNavigationMain, navigation)
        navigation.addOnDestinationChangedListener { controller: NavController?, destination: NavDestination, arguments: Bundle? ->
            val destination = destination.id
            if (destination == R.id.listMapelFragment || destination == R.id.detailMapelActivity )
             hidebottomnav() else showbottomnav()
        }
    }
    private fun hidebottomnav() {
        binding.bottomNavigationMain.visibility = View.GONE
        binding.ivLapor.visibility = View.GONE
    }

    fun showbottomnav() {
        binding.bottomNavigationMain.visibility = View.VISIBLE
        binding.ivLapor.visibility = View.VISIBLE
    }
}