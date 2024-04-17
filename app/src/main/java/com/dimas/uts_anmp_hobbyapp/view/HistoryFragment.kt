package com.dimas.uts_anmp_hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dimas.uts_anmp_hobbyapp.R
import com.dimas.uts_anmp_hobbyapp.databinding.FragmentHistoryBinding
import com.dimas.uts_anmp_hobbyapp.viewmodel.NavViewModel

class HistoryFragment : Fragment() {
    private  lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: NavViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(NavViewModel::class.java)
        viewModel.selectedItemId.observe(viewLifecycleOwner) { itemId ->
            when (itemId) {
                R.id.itemHome -> {
                    val action = HistoryFragmentDirections.actionHistoryToHome()
                    Navigation.findNavController(requireView()).navigate(action)
                }

                R.id.itemUsers -> {
                    val action = HistoryFragmentDirections.actionHistoryToProfile()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
        }
    }
}