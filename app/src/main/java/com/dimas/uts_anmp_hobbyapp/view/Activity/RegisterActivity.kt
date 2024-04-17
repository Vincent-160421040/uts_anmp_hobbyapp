package com.dimas.uts_anmp_hobbyapp.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dimas.uts_anmp_hobbyapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp2.setOnClickListener {
            if(binding.txtPassword.text.toString() == binding.txtConfirmPassword.text.toString()) {
                registerUser()
            }
            else {
                Toast.makeText(this,"Password does not match", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignIn3.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser() {
        val userId      = binding.txtUsername.text.toString()
        val firstName   = binding.txtFirstName.text.toString()
        val lastname    = binding.txtLastName.text.toString()
        val pass        = binding.txtPassword.text.toString()

        val url = "https://ubaya.me/native/160421040/hobby_register.php"

        val request = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response -> Toast.makeText(this, "Registration success!", Toast.LENGTH_SHORT).show() },
            Response.ErrorListener { error -> Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show() }) {

            override fun getParams(): HashMap<String, String>
            {
                val params = HashMap<String, String>()

                params["iduser"]        = userId
                params["nama_depan"]    = firstName
                params["nama_belakang"] = lastname
                params["password"]      = pass

                return params
            }
        }

        Volley.newRequestQueue(this).add(request)
    }
}