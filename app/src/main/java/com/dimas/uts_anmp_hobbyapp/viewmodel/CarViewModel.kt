package com.dimas.uts_anmp_hobbyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dimas.uts_anmp_hobbyapp.model.Car
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarViewModel(application: Application): AndroidViewModel(application) {
    val TAG             = "volleyCarTag"
    val carLD           = MutableLiveData<ArrayList<Car>>()
    val loadingLD       = MutableLiveData<Boolean>()
    val carLoadErrorLD  = MutableLiveData<Boolean>()

    private var queue: RequestQueue?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun refresh(){
        loadingLD.value         = true
        carLoadErrorLD.value    = false

        val url = "http://192.168.100.31/json/car.json"
        queue   = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                Log.d("show_volley", it)

                val sType = object: TypeToken<List<Car>>() {}.type
                val result = Gson().fromJson<List<Car>>(it, sType)

                carLD.value = result as ArrayList<Car>
            },
            {
                Log.d("show_volley", it.toString())
            }
        )
        stringRequest.tag =TAG
        queue?.add(stringRequest)
    }
}