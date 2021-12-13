package com.algebra.mobiletaskmvvm.user_details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.algebra.mobiletaskmvvm.Browser
import com.algebra.mobiletaskmvvm.R
import com.algebra.mobiletaskmvvm.databinding.FragmentUserDetailsBinding
import com.bumptech.glide.Glide

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var externalUrl : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_user_details, container, false)

        var viewModelFactory = UserDetailViewModelFactory(requireArguments().getString("owner", ""), requireArguments().getString("repo", ""))
        var viewModel = ViewModelProvider(this, viewModelFactory).get(UserDetailViewModel::class.java)

        binding.openUserDetailInBrowserButton.setOnClickListener {
            Browser.open(container!!.context, externalUrl)
        }

        viewModel.userDetail.observe(viewLifecycleOwner, {
            Glide.with(this).load(it.avatarURL).into(binding.authorImage)
            binding.authorNameText.text         = it.authorName
            externalUrl                         = it.userUrl
        })

        return binding.root
    }

}