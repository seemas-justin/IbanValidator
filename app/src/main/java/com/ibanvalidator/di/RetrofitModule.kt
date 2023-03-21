package com.ibanvalidator.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibanvalidator.core.Constants
import com.ibanvalidator.data.IbanRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideIbanValidatorRemoteDataSource(retrofit: Retrofit.Builder): IbanRemoteDataSource {
        return retrofit.build().create(IbanRemoteDataSource::class.java)
    }
}