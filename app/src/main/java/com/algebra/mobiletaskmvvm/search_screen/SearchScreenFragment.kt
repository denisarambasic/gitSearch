package com.algebra.mobiletaskmvvm.search_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.algebra.mobiletaskmvvm.R
import com.algebra.mobiletaskmvvm.databinding.FragmentSearchScreenBinding


class SearchScreenFragment : Fragment() {

    private lateinit var binding: FragmentSearchScreenBinding
    private lateinit var adapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_search_screen, container, false)

        adapter = RepositoryAdapter()
        binding.repositoriesRecyclerView.adapter = adapter

        var viewModel = ViewModelProvider(this).get(SearchScreenViewModel::class.java)


        binding.searchButton.setOnClickListener {
            if(binding.rbNone.isChecked)
                viewModel.searchRepositoryByName(binding.repositorySearchNameEditText.text.toString())
            if(binding.rbForks.isChecked)
                viewModel.searchRepositoryByNameAndSortPropertyDesc(binding.repositorySearchNameEditText.text.toString(), "forks")
            if(binding.rbStars.isChecked)
                viewModel.searchRepositoryByNameAndSortPropertyDesc(binding.repositorySearchNameEditText.text.toString(),"stars")
            if(binding.rbUpdated.isChecked)
                viewModel.searchRepositoryByNameAndSortPropertyDesc(binding.repositorySearchNameEditText.text.toString(), "updated")

        }


        viewModel.repositories.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty())
                adapter.repositories = it
            else
                Log.i("SearchScreenFragment", "Nema repozitorija")
        })

        return binding.root
    }
}