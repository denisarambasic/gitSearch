package com.algebra.mobiletaskmvvm.repository_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.algebra.mobiletaskmvvm.models.*
import com.algebra.mobiletaskmvvm.network.GithubApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepoDetailViewModel(private var owner: String, private var repo: String) : ViewModel() {
    private val _repoDetail = MutableLiveData<RepoDetail>()
    val repoDetail: LiveData<RepoDetail>
        get() = _repoDetail

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        searchUserDetail()
    }

    private fun searchUserDetail(){
        coroutineScope.launch {
            var response = GithubApi.retrofitService.getRepository(owner, repo).await()
            _repoDetail.value = response.repoDetail()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}