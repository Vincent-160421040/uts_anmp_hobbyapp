package com.dimas.uts_anmp_hobbyapp.view.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dimas.uts_anmp_hobbyapp.databinding.ActivityLoginBinding
import com.dimas.uts_anmp_hobbyapp.model.User
import com.dimas.uts_anmp_hobbyapp.view.UserLoginClickListener
import com.dimas.uts_anmp_hobbyapp.view.UserRegisterClickListener
import com.dimas.uts_anmp_hobbyapp.viewmodel.UserViewModel
import org.json.JSONObject

class LoginActivity : AppCompatActivity(), UserLoginClickListener, UserRegisterClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.user = User("","","","","")

        binding.loginListener = this
        binding.registerListener = this
    }

    override fun onUserRegisterClickListener(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onUserLoginClickListener(v: View) {
        val username = binding.txtUsername.text.toString()
        val password = binding.txtPassword.text.toString()

        viewModel.loginUser(username, password).observe(this, Observer { user ->
            if (user != null) {
                val loginInfo = "com.dimas.uts_anmp_hobbyapp"
                val shared: SharedPreferences = getSharedPreferences(loginInfo, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = shared.edit()

                editor.putString("iduser", username)
                editor.putString("name", user.nama_depan)
                editor.apply()

                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed()
    {
        var loginInfo = "com.dimas.uts_anmp_hobby"
        var shared:SharedPreferences = getSharedPreferences(loginInfo, Context.MODE_PRIVATE )
        var userid = shared.getString("iduser","").toString()

        if(userid == ""){
            Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show()
        }else{
            super.onBackPressed()
        }
    }
}