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
import androidx.lifecycle.Observer
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

class ProfileFragment : Fragment(), UserUpdateClickListener, UserLogoutClickListener{
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

        binding.updateListener = this
        binding.logoutListener = this

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getData(MainActivity.userid)
        observeViewModel()

        navViewModel    = ViewModelProvider(requireActivity()).get(NavViewModel::class.java)
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
    }

    fun observeViewModel() {
        userViewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.user = it
        })
    }

    override fun onUserUpdateClickListener(v: View) {
        userViewModel.updateUser(binding.user!!)
        Toast.makeText(context, "Detail Updated", Toast.LENGTH_SHORT).show()
    }

    override fun onUserLogoutClickListener(v: View) {
        var loginInfo = "com.dimas.uts_anmp_hobbyapp"
        var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo, Context.MODE_PRIVATE )
        var editor: SharedPreferences.Editor = shared.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(v.context, LoginActivity::class.java)
        v.context.startActivity(intent)
    }
}