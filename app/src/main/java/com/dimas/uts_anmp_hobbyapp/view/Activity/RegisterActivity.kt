package com.dimas.uts_anmp_hobbyapp.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dimas.uts_anmp_hobbyapp.databinding.ActivityRegisterBinding
import com.dimas.uts_anmp_hobbyapp.model.User
import com.dimas.uts_anmp_hobbyapp.view.UserLoginClickListener
import com.dimas.uts_anmp_hobbyapp.view.UserRegisterClickListener
import com.dimas.uts_anmp_hobbyapp.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity(), UserRegisterClickListener, UserLoginClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.user = User("","","","","")

        binding.registerListener = this
        binding.loginListener = this
    }

    override fun onUserRegisterClickListener(v: View) {
        if(binding.txtPassword.text.toString() == binding.txtConfirmPassword.text.toString()) {
            viewModel.addUser(listOf(binding.user!!))

            Toast.makeText(v.context, "User Registered Successfully", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this,"Password does not match", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUserLoginClickListener(v: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
