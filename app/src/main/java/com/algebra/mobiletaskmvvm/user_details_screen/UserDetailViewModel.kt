package com.algebra.mobiletaskmvvm.user_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.algebra.mobiletaskmvvm.models.Repository
import com.algebra.mobiletaskmvvm.models.UserDetail
import com.algebra.mobiletaskmvvm.models.repositories
import com.algebra.mobiletaskmvvm.models.userDetail
import com.algebra.mobiletaskmvvm.network.GithubApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserDetailViewModel(private var owner: String, private var repo: String) : ViewModel() {
    private val _userDetail = MutableLiveData<UserDetail>()
    val userDetail: LiveData<UserDetail>
        get() = _userDetail

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        searchUserDetail()
    }

    private fun searchUserDetail(){
        coroutineScope.launch {
            var response = GithubApi.retrofitService.getRepository(owner, repo).await()
            _userDetail.value = response.userDetail()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}