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
import com.nori.itfetch.databinding.FragmentAiBinding

class AiFragment : Fragment() {
    private var _binding: FragmentAiBinding? = null
    private val binding get() = _binding as FragmentAiBinding

    private var _aiNewsViewModel: NewsViewModel? = null
    private val aiNewsViewModel get() = _aiNewsViewModel as NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAiBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _aiNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        aiNewsViewModel.aiNews()
        aiNewsViewModel.AiNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "AiFragment",
                "onViewCreated: ${it.articles}"
            )
            binding.rvAi.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}