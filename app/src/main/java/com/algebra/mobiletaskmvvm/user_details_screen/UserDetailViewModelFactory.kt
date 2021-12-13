package com.algebra.mobiletaskmvvm.user_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserDetailViewModelFactory(private var owner: String, private var repo: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(owner, repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}