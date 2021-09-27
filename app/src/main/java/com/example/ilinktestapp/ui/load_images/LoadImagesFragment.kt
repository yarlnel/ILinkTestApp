package com.example.ilinktestapp.ui.load_images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ilinktestapp.appComponent
import com.example.ilinktestapp.databinding.FragmentLoadImagesBinding

class LoadImagesFragment : Fragment() {

    private val loadImagesViewModel: LoadImagesViewModel by lazy {
        ViewModelProvider(requireActivity())
            .get(LoadImagesViewModel::class.java)
    }

    private var _binding: FragmentLoadImagesBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.appComponent?.let {
            loadImagesViewModel.injectDI(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoadImagesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.imageRecyclerView.adapter = loadImagesViewModel.imageRecyclerViewAdapter
        binding.imageRecyclerView.layoutManager = loadImagesViewModel.linerLayoutManager

        loadImagesViewModel.images.observe(viewLifecycleOwner, Observer {
            loadImagesViewModel.imageRecyclerViewAdapter.setImages(it)
        })

        binding.fabDeleteImagesFromDB.setOnClickListener {
            loadImagesViewModel.deleteAllImagesFromDB()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
