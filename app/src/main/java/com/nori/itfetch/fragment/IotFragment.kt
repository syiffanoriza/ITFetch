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
import com.nori.itfetch.databinding.FragmentIotBinding

class IotFragment : Fragment() {
    private var _binding: FragmentIotBinding? = null
    private val binding get() = _binding as FragmentIotBinding

    private var _iotNewsViewModel: NewsViewModel? = null
    private val iotNewsViewModel get() = _iotNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIotBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _iotNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        iotNewsViewModel.iotNews()
        iotNewsViewModel.IotNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "IotFragment",
                "onViewCreated: ${it.articles}"
            )
            binding.rvIot.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }

}