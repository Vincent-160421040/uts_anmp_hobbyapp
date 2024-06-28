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
import com.dimas.uts_anmp_hobbyapp.model.CarDatabase
import com.dimas.uts_anmp_hobbyapp.model.UserDatabase
import com.dimas.uts_anmp_hobbyapp.util.buildCarDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CarDetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val carLD = MutableLiveData<Car>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(carId: Int){
        launch{
            carLD.postValue(buildCarDb(getApplication()).CarDao().selectCar(carId))
        }
    }
}
