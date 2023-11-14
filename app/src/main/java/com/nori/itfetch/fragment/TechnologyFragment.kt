package com.nori.itfetch.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nori.itfetch.NewsViewModel
import com.nori.itfetch.adapter.NewsAdapter
import com.nori.itfetch.databinding.FragmentTechnologyBinding

class TechnologyFragment : Fragment() {
    private var _binding: FragmentTechnologyBinding? = null
    private val binding get() = _binding as FragmentTechnologyBinding

    private var _technologyNewsViewModel: NewsViewModel? = null
    private val technologyNewsViewModel get() = _technologyNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTechnologyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _technologyNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        technologyNewsViewModel.technologyNews()
        technologyNewsViewModel.technologyNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "TechnologyFragment",
                "onViewCreated: ${it.articles}"
            )
            binding.rvTechnology.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}