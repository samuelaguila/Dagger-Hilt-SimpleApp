package com.saam.dagger_hilt_basicapp.repository

import com.saam.dagger_hilt_basicapp.retrofit.BlogNetworkEntity
import com.saam.dagger_hilt_basicapp.retrofit.BlogRetrofit
import com.saam.dagger_hilt_basicapp.retrofit.NetworkMapper
import com.saam.dagger_hilt_basicapp.room.BlogDao
import com.saam.dagger_hilt_basicapp.room.CacheMapper

class MainRepository
constructor(
    private val blogRetrofit: BlogRetrofit,
){

    suspend fun getBlogs(): List<BlogNetworkEntity>{
        return blogRetrofit.get()
    }
}
