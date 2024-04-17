package com.dimas.uts_anmp_hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dimas.uts_anmp_hobbyapp.databinding.FragmentCarNewsBinding
import com.squareup.picasso.Picasso

class CarNewsFragment : Fragment() {
    private lateinit var  binding:FragmentCarNewsBinding

    private var carId       :Int? = null
    private var newsIndex   : Int = 0

    private lateinit var title          : String
    private lateinit var creator        : String
    private lateinit var description    : List<String>
    private lateinit var image          : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carId           = arguments?.getInt("carId")
        title           = CarListAdapter.title
        creator         = CarListAdapter.creator
        description     = CarListAdapter.news
        image           = CarListAdapter.image

        refresh()

        binding.btnPrev.setOnClickListener {
            if (newsIndex > 0) {
                newsIndex--
            }
            refresh()
        }

        binding.btnNext.setOnClickListener {
            if (newsIndex < description.size - 1) {
                newsIndex++
            }
            refresh()
        }

        binding.btnHome.setOnClickListener {
            val action = CarNewsFragmentDirections.actionNewsToHome()
            Navigation.findNavController(view).navigate(action)
        }
    }

    fun refresh(){
        if (carId != null){
            val builder = Picasso.Builder(requireContext())
            builder.listener { picasso, uri, exception -> exception.printStackTrace() }
            Picasso.get().load(image).into(binding.imgNews)

            binding.txtNewsTitle.text       = title
            binding.txtNewsCreator.text     = "@" + creator
            binding.txtNews.text            = description[newsIndex]
            binding.txtPage.text = (newsIndex + 1).toString() + "/" + description.size.toString()
        }
    }
}