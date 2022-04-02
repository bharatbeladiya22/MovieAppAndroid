package com.imdb.movieapp.di

import android.content.Context
import com.imdb.movieapp.BuildConfig
import com.imdb.movieapp.api.ApiHelper
import com.imdb.movieapp.api.ApiHelperImpl
import com.imdb.movieapp.api.ApiService
import com.imdb.movieapp.repository.MovieRepository
import com.imdb.movieapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserRepo(apiHelper: ApiHelper): MovieRepository {
        return MovieRepository(apiHelper)
    }


    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .writeTimeout(600, TimeUnit.SECONDS) // write timeout
            .addInterceptor { chain ->
                val original = chain.request()

                // Customize the request
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .build()
                val response = chain.proceed(request)
                response
            }

        return if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(loggingInterceptor)
                .build()
        } else {
            client.build()
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(provideBaseUrl())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}