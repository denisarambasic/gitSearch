package com.algebra.mobiletaskmvvm.search_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.algebra.mobiletaskmvvm.models.Repository
import com.algebra.mobiletaskmvvm.models.repositories
import com.algebra.mobiletaskmvvm.network.GithubApi
import kotlinx.coroutines.*

class SearchScreenViewModel : ViewModel() {
    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = _repositories

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun searchRepositoryByName(query: String){
        coroutineScope.launch {
            var response = GithubApi.retrofitService.getRepositories(query).await()
            _repositories.value = response.repositories()
        }
    }

    fun searchRepositoryByNameAndSortPropertyDesc(query: String, sortedValue: String){
        coroutineScope.launch {
            var response = GithubApi.retrofitService.getRepositories(query, sortedValue, "desc").await()
            _repositories.value = response.repositories()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}