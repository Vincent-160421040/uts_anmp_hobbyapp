package com.dimas.uts_anmp_hobbyapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dimas.uts_anmp_hobbyapp.R
import com.dimas.uts_anmp_hobbyapp.databinding.FragmentProfileBinding
import com.dimas.uts_anmp_hobbyapp.model.User
import com.dimas.uts_anmp_hobbyapp.viewmodel.NavViewModel
import com.dimas.uts_anmp_hobbyapp.viewmodel.UserViewModel
import com.dimas.uts_anmp_hobbyapp.view.Activity.LoginActivity
import com.dimas.uts_anmp_hobbyapp.view.Activity.MainActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.json.JSONObject

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var navViewModel: NavViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        UpdateUI()

        navViewModel = ViewModelProvider(requireActivity()).get(NavViewModel::class.java)
        navViewModel.selectedItemId.observe(viewLifecycleOwner) { itemId ->
            when (itemId) {
                R.id.itemHome -> {
                    val action = ProfileFragmentDirections.actionProfileToHome()
                    Navigation.findNavController(requireView()).navigate(action)
                }

                R.id.itemHistory -> {
                    val action = ProfileFragmentDirections.actionProfileToHistory()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
        }

        binding.btnSignOut.setOnClickListener {
            var loginInfo = "com.dimas.uts_anmp_hobbyapp"
            var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo, Context.MODE_PRIVATE )
            var editor: SharedPreferences.Editor = shared.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(it.context, LoginActivity::class.java)
            it.context.startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener {
            if (binding.txtNewPassword.text.toString() == binding.txtConfirmPassword.text.toString()) {
                UpdateProfile()
                UpdateUI()
            }
            else if(binding.txtConfirmPassword.text.toString()  == "" ||
                    binding.txtNewPassword.text.toString()      == "" ||
                    binding.txtOldPassword.text.toString()      == "")
            {
                Toast.makeText(requireContext(), "Please input password", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun UpdateProfile() {
        val password        = binding.txtNewPassword.text.toString()
        val email           = binding.txtEmail2.text.toString()
        val namaDepan       = binding.txtFirstname2.text.toString()
        val namaBelakang    = binding.txtLastName2.text.toString()

        val url = "https://ubaya.me/native/160421040/hobby_update_profile.php"

        val request = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()
            },

            Response.ErrorListener { error ->
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()

                params["iduser"]        = MainActivity.userid
                params["email"]         = email
                params["password"]      = password
                params["nama_depan"]    = namaDepan
                params["nama_belakang"] = namaBelakang

                return params
            }
        }
        Volley.newRequestQueue(requireContext()).add(request)
    }

    private fun UpdateUI(){
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getData()
        userViewModel.userLD.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.txtName.text = "Welcome, " + user.nama_depan + " " + user.nama_belakang
                binding.txtEmail2.setText(user.email)
                binding.txtFirstname2.setText(user.nama_depan)
                binding.txtLastName2.setText(user.nama_belakang)
            }
        }
    }
}