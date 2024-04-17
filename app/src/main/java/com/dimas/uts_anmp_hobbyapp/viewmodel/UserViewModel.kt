package com.dimas.uts_anmp_hobbyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dimas.uts_anmp_hobbyapp.model.User
import com.dimas.uts_anmp_hobbyapp.view.Activity.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "volleyUserTag"
    val userLD = MutableLiveData<User>()
    val loadingLD = MutableLiveData<Boolean>()
    val userLoadErrorLD = MutableLiveData<Boolean>()

    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun getData() {
        loadingLD.value = true
        userLoadErrorLD.value = false

        val url = "https://ubaya.me/native/160421040/hobby_get_user.php?id=" + MainActivity.userid
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                Log.d("show_volley", it)

                val sType = object : TypeToken<User>() {}.type
                val result = Gson().fromJson<User>(it, sType)

                userLD.value = result
            },
            {
                Log.d("show_volley", it.toString())
            }
        )
        stringRequest.tag =TAG
        queue?.add(stringRequest)
    }
}