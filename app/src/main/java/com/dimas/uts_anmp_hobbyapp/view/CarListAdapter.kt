package com.dimas.uts_anmp_hobbyapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dimas.uts_anmp_hobbyapp.databinding.CarListItemBinding
import com.dimas.uts_anmp_hobbyapp.model.Car

class CarListAdapter(val carList: ArrayList<Car>) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>(), CarReadMoreClickListener {

    class CarViewHolder(var binding: CarListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarListItemBinding.inflate(inflater, parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]

        holder.binding.car = car
        holder.binding.readMoreListener = this
        holder.binding.btnMore.tag = car.id
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun updateCarList(newCarList: List<Car>) {
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
        Log.d("CarListAdapter", "Car list updated: $carList")
    }

    override fun onCarReadMoreClickListener(v: View) {
        val action = HomeFragmentDirections.actionHomeToNews(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}
