package com.dimas.uts_anmp_hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dimas.uts_anmp_hobbyapp.databinding.FragmentCarNewsBinding
import com.dimas.uts_anmp_hobbyapp.viewmodel.CarDetailViewModel

class CarNewsFragment : Fragment(), CarBackClickListener {
    private lateinit var binding: FragmentCarNewsBinding
    private lateinit var carDetailViewModel: CarDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carDetailViewModel = ViewModelProvider(this).get(CarDetailViewModel::class.java)

        val carId = CarNewsFragmentArgs.fromBundle(requireArguments()).carId
        carDetailViewModel.fetch(carId)

        binding.backListener = this

        observeViewModel()
    }

    private fun observeViewModel() {
        carDetailViewModel.carLD.observe(viewLifecycleOwner, Observer { car ->
            binding.car = car
        })
    }

    override fun onCarBackClickListener(v: View) {
        val action = CarNewsFragmentDirections.actionNewsToHome()
        Navigation.findNavController(v).navigate(action)
    }
}
