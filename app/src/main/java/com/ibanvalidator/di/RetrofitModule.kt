package com.ibanvalidator.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibanvalidator.core.Constants
import com.ibanvalidator.data.IbanValidatorRepositoryImpl
import com.ibanvalidator.data.api.IbanValidationService
import com.ibanvalidator.domain.IbanValidatorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesIbanRepository(ibanValidationService: IbanValidationService, gson: Gson):IbanValidatorRepository {
        return IbanValidatorRepositoryImpl(ibanValidationService, gson)
    }
    @Singleton
    @Provides
    fun providesIbanApiService(retrofit: Retrofit) : IbanValidationService{
        return retrofit.create(IbanValidationService::class.java)
    }

    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient{
       return OkHttpClient.Builder()
           .addInterceptor(HeaderInterceptor())
           .build()
    }


}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
         proceed(
            request()
                .newBuilder()
                .addHeader("apikey", Constants.API_KEY)
                .build()
        )

    }

}

