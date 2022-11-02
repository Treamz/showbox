package com.treamz.showbox.di

import com.treamz.showbox.common.Constants
import com.treamz.showbox.data.remote.TheMovieDBApi
import com.treamz.showbox.data.repository.MovieRepositoryImpl
import com.treamz.showbox.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTheMoveDbApi(): TheMovieDBApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TheMovieDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(theMovieDBApi: TheMovieDBApi): MovieRepository {
        return MovieRepositoryImpl(theMovieDBApi)
    }
}