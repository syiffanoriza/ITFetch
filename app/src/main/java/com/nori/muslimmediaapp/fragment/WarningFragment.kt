package com.nori.muslimmediaapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nori.muslimmediaapp.NewsViewModel
import com.nori.muslimmediaapp.adapter.NewsAdapter
import com.nori.muslimmediaapp.databinding.FragmentWarningBinding

class WarningFragment : Fragment() {
    private var _binding: FragmentWarningBinding? = null
    private val binding get() = _binding as FragmentWarningBinding

    private var _warningNewsViewModel: NewsViewModel? = null
    private val warningNewsViewModel get() = _warningNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWarningBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _warningNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        warningNewsViewModel.warningForMuslimNews()
        warningNewsViewModel.warningForMuslimNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "WarningFragment",
                "onViewCreated: ${it.articles}"
            )
            binding.rvWarning.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}