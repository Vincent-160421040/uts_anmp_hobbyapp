package com.dimas.uts_anmp_hobbyapp.view.Activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dimas.uts_anmp_hobbyapp.databinding.ActivityMainBinding
import com.dimas.uts_anmp_hobbyapp.viewmodel.NavViewModel

class MainActivity : AppCompatActivity(){
    private lateinit var binding    : ActivityMainBinding
    private lateinit var viewModel  : NavViewModel

    companion object{
        var userid:String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLoginInfo()
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this).get(NavViewModel::class.java)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            viewModel.BottomNavItem(item.itemId)
            true
        }
    }

    fun getLoginInfo(){
        var loginInfo = "com.dimas.uts_anmp_hobbyapp"
        var shared: SharedPreferences = getSharedPreferences(loginInfo, Context.MODE_PRIVATE )
        userid = shared.getString("iduser","").toString()
    }

    override fun onBackPressed() {
        if(userid != "")
        {
            Toast.makeText(this, "Please Click Logout", Toast.LENGTH_SHORT).show()
        }
        else
        {
            super.onBackPressed()
        }
    }
}