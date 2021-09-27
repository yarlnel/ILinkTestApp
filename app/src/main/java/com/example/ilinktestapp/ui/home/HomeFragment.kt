package com.example.ilinktestapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ilinktestapp.R
import com.example.ilinktestapp.appComponent
import com.example.ilinktestapp.databinding.FragmentHomeBinding
import com.example.ilinktestapp.listeners.OnDoubleClickListener


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.appComponent?.let {
            homeViewModel.injectDi(it)
        }
        lifecycle.addObserver(homeViewModel)
    }

    

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.animalImageView.setOnClickListener(OnDoubleClickListener {
            homeViewModel.pushCurrentImageIntoDB()
            Toast.makeText(
                activity,
                resources.getString(R.string.image_saved),
                Toast.LENGTH_SHORT
            ).show()

        })

        homeViewModel.imageBitmap.observe(viewLifecycleOwner, Observer {
            binding.animalImageView.setImageBitmap(it)
            binding.imageProgressBar.visibility = ProgressBar.INVISIBLE
        })

        val showImageProgressBar = {
            binding.imageProgressBar.visibility = ProgressBar.VISIBLE
        }

        binding.btnFox.setOnClickListener {
            homeViewModel.showRandomFoxImage()
            showImageProgressBar()
        }
        binding.btnCat.setOnClickListener {
            homeViewModel.showRandomCatImage()
            showImageProgressBar()
        }
        binding.btnDuck.setOnClickListener {
            homeViewModel.showRandomDuckImage()
            showImageProgressBar()
        }
        binding.btnDog.setOnClickListener {
            homeViewModel.showRandomDogImage()
            showImageProgressBar()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}