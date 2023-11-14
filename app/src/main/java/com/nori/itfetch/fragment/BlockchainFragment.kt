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
import com.nori.itfetch.databinding.FragmentBlockchainBinding

class BlockchainFragment : Fragment() {
    private var _binding: FragmentBlockchainBinding? = null
    private val binding get() = _binding as FragmentBlockchainBinding

    private var _blockchainNewsViewModel: NewsViewModel? = null
    private val blockchainNewsViewModel get() = _blockchainNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlockchainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _blockchainNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        blockchainNewsViewModel.blockchainNews()
        blockchainNewsViewModel.BlockchainNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "BlockchainFragment",
                "onViewCreated: ${it.articles}"
            )
            binding.rvBlockchain.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}