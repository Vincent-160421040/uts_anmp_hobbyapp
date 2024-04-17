package com.dimas.uts_anmp_hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimas.uts_anmp_hobbyapp.R
import com.dimas.uts_anmp_hobbyapp.databinding.FragmentHomeBinding
import com.dimas.uts_anmp_hobbyapp.viewmodel.CarViewModel
import com.dimas.uts_anmp_hobbyapp.viewmodel.NavViewModel


class HomeFragment : Fragment() {
    private lateinit var binding        : FragmentHomeBinding
    private lateinit var navViewModel   : NavViewModel
    private lateinit var carViewModel   : CarViewModel
    private val carListAdapter = CarListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        carViewModel.refresh()

        observeViewModel()

        binding.recView.adapter         = carListAdapter
        binding.recView.visibility      = View.VISIBLE
        binding.recView.layoutManager   = LinearLayoutManager(context)

        binding.refreshLayout.setOnRefreshListener {
            carViewModel.refresh()
            binding.recView.visibility          = View.GONE
            binding.txtError.visibility         = View.GONE
            binding.progressLoad.visibility     = View.VISIBLE
            binding.refreshLayout.isRefreshing  = false
        }

        navViewModel = ViewModelProvider(requireActivity()).get(NavViewModel::class.java)
        navViewModel.selectedItemId.observe(viewLifecycleOwner) { itemId ->
            when (itemId) {
                R.id.itemHistory -> {
                    val action = HomeFragmentDirections.actionHomeToHistory()
                    Navigation.findNavController(requireView()).navigate(action)
                }

                R.id.itemUsers -> {
                    val action = HomeFragmentDirections.actionHomeToProfile()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
        }
    }

    fun observeViewModel() {
        carViewModel.carLD.observe(viewLifecycleOwner, Observer {
            carListAdapter.updateCarList(it)
        })

        carViewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer
        {
            if (it == true) {
                binding.txtError.visibility = View.VISIBLE
            }
            else {
                binding.txtError.visibility = View.GONE
            }
        })

        carViewModel.loadingLD.observe(viewLifecycleOwner, Observer
        {
            if (it == true) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recView.visibility = View.GONE
            }
            else {
                binding.progressLoad.visibility = View.GONE
                binding.recView.visibility = View.VISIBLE
            }
        })
    }
}