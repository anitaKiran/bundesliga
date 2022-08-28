package com.kickbase.matches.di

import com.kickbase.matches.BuildConfig
import com.kickbase.matches.data.network.KickbaseServiceApi
import com.kickbase.matches.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Anita Kiran on 8/27/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // okhttp
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                chain.proceed(newRequest.build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    // Retrofit
    @Provides
    @Singleton
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    // Retrofit service
    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : KickbaseServiceApi =
        retrofit.create(KickbaseServiceApi::class.java)
 }
