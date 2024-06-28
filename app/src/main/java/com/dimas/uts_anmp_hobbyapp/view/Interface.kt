package com.dimas.uts_anmp_hobbyapp.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

interface UserUpdateClickListener{
    fun onUserUpdateClickListener(v: View)
}

interface UserRegisterClickListener{
    fun onUserRegisterClickListener(v: View)
}

interface UserLoginClickListener{
    fun onUserLoginClickListener(v: View)
}

interface UserLogoutClickListener{
    fun onUserLogoutClickListener(v: View)
}

interface CarReadMoreClickListener{
    fun onCarReadMoreClickListener(v: View)
}

interface CarBackClickListener{
    fun onCarBackClickListener(v: View)
}

@BindingAdapter("urlGambar")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Picasso.get()
            .load(url)
            .into(view)
    }
}

