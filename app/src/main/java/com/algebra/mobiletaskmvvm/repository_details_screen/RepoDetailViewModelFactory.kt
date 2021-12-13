package com.algebra.mobiletaskmvvm.repository_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepoDetailViewModelFactory(private var owner: String, private var repo: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoDetailViewModel::class.java)) {
            return RepoDetailViewModel(owner, repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}