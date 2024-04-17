package com.dimas.uts_anmp_hobbyapp.viewmodel

import android.adservices.adid.AdId
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavViewModel : ViewModel() {
    private val ItemId = MutableLiveData<Int>()
    val selectedItemId: LiveData<Int> = ItemId

    fun onBottomNavigationItemSelected(itemId: Int) {
        ItemId.value = itemId
    }
}