package com.saam.dagger_hilt_basicapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saam.dagger_hilt_basicapp.model.Blog
import com.saam.dagger_hilt_basicapp.repository.MainRepository
import com.saam.dagger_hilt_basicapp.retrofit.NetworkMapper
import com.saam.dagger_hilt_basicapp.room.BlogDao
import com.saam.dagger_hilt_basicapp.room.CacheMapper
import com.saam.dagger_hilt_basicapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
    private val blogDao: BlogDao
): ViewModel() {

    fun getBlogs(): LiveData<Resource<List<Blog>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        delay(1000)
        try{
            val networkBlogs = mainRepository.getBlogs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(Resource.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(Resource.Error(e))
        }
    }

}



















