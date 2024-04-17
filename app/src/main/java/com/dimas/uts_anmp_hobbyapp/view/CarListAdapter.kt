package com.dimas.uts_anmp_hobbyapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dimas.uts_anmp_hobbyapp.databinding.CarListItemBinding
import com.dimas.uts_anmp_hobbyapp.model.Car
import com.squareup.picasso.Picasso

class CarListAdapter(val carList:ArrayList<Car>) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    companion object {
        var title: String   = ""
        var creator: String = ""
        var image: String   = ""
        var news: ArrayList<String> = ArrayList()
    }

    class CarViewHolder(var binding: CarListItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        var binding = CarListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception -> exception.printStackTrace() }
        Picasso.get().load(carList[position].images).into(holder.binding.imgPoster)

        holder.binding.txtTitle.text        = carList[position].title
        holder.binding.txtCreator.text      = "@" + carList[position].creator
        holder.binding.txtDescription.text  = carList[position].descriptions[0]

        holder.binding.btnMore.setOnClickListener { view ->
            title       = carList[position].title
            creator     = carList[position].creator
            news        = carList[position].descriptions
            image       = carList[position].images

            val action = HomeFragmentDirections.actionHomeToNews(carList[position].id)
            Navigation.findNavController(view).navigate(action)
        }
    }

    fun updateCarList(newCarList: ArrayList<Car>){
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
        Log.d("CarListAdapter", "Car list updated: $carList")
    }
}