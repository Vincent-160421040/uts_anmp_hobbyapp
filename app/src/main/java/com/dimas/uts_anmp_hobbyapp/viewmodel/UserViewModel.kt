package com.dimas.uts_anmp_hobbyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dimas.uts_anmp_hobbyapp.model.User
import com.dimas.uts_anmp_hobbyapp.model.UserDatabase
import com.dimas.uts_anmp_hobbyapp.util.buildUserDb
import com.dimas.uts_anmp_hobbyapp.view.Activity.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {

    val userLD = MutableLiveData<User>()
    val loadingLD = MutableLiveData<Boolean>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


    fun getData(userId: String) {
        loadingLD.value = true
        userLoadErrorLD.value = false

        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )

            userLD.postValue(db.UserDao().selectUser(userId))
            loadingLD.postValue(false)
        }
    }

    fun addUser(list: List<User>){
        launch {
            val db = buildUserDb(
                getApplication()
            )

            list.forEach { user ->
                db.UserDao().insertUser(user)
            }
        }
    }

    fun updateUser(user: User) {
        launch {
            val db = buildUserDb(
                getApplication()
            )

            db.UserDao().updateUser(user)
        }
    }

    fun loginUser(id: String, password: String): LiveData<User?> = liveData(Dispatchers.IO) {
        val db = buildUserDb(
            getApplication()
        )

        val user = db.UserDao().loginUser(id, password)
        emit(user)
    }
}