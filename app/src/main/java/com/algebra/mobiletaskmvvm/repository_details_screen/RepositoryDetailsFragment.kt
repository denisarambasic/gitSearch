package com.algebra.mobiletaskmvvm.repository_details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.algebra.mobiletaskmvvm.Browser
import com.algebra.mobiletaskmvvm.R
import com.algebra.mobiletaskmvvm.databinding.FragmentRepositoryDetailsBinding
import com.algebra.mobiletaskmvvm.databinding.FragmentUserDetailsBinding
import com.algebra.mobiletaskmvvm.user_details_screen.UserDetailViewModel
import com.algebra.mobiletaskmvvm.user_details_screen.UserDetailViewModelFactory
import com.bumptech.glide.Glide


class RepositoryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryDetailsBinding
    private lateinit var externalUrl : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_repository_details, container, false)

        var owner = requireArguments().getString("owner", "")
        var repo = requireArguments().getString("repo", "")

        var viewModelFactory = RepoDetailViewModelFactory(owner, repo)
        var viewModel = ViewModelProvider(this, viewModelFactory).get(RepoDetailViewModel::class.java)

        binding.userDetailsButton.setOnClickListener {
            Navigation.findNavController(it).navigate(RepositoryDetailsFragmentDirections.actionRepositoryDetailsFragmentToUserDetailsFragment(owner, repo))
        }

        binding.openRepoInBrowserButton.setOnClickListener {
            Browser.open(container!!.context, externalUrl)
        }

        viewModel.repoDetail.observe(viewLifecycleOwner, {
            binding.repositoryNameText.text     = it.repositoryName
            binding.createdAtNameText.text      = it.createdAt
            binding.updatedAtText.text          = it.updatedAt
            binding.programingLanguageText.text = it.language
            externalUrl                         = it.repositoryUrl
        })

        return binding.root
    }
}