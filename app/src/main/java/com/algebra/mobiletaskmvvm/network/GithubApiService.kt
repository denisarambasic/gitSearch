package com.algebra.mobiletaskmvvm.network

import com.algebra.mobiletaskmvvm.models.RepoResponse
import com.algebra.mobiletaskmvvm.models.RepositoryResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.github.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface GithubApiService {
    @GET("search/repositories")
    fun getRepositories(@Query("q") query : String) : Deferred<RepositoryResponse>

    @GET("search/repositories")
    fun getRepositories(@Query("q") query : String, @Query("sort") sort : String, @Query("order") order : String) : Deferred<RepositoryResponse>

    @GET("repos/{owner}/{repo}")
    fun getRepository(@Path("owner") owner: String, @Path("repo") repo: String) : Deferred<RepoResponse>
}

object GithubApi {
    val retrofitService : GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}