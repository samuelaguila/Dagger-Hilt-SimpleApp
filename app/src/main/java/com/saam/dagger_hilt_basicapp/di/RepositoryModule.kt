package com.saam.dagger_hilt_basicapp.di

import com.saam.dagger_hilt_basicapp.repository.MainRepository
import com.saam.dagger_hilt_basicapp.retrofit.BlogRetrofit
import com.saam.dagger_hilt_basicapp.retrofit.NetworkMapper
import com.saam.dagger_hilt_basicapp.room.BlogDao
import com.saam.dagger_hilt_basicapp.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        retrofit: BlogRetrofit,
    ): MainRepository {
        return MainRepository(retrofit)
    }
}














