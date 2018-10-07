package com.pavanpathro.dagger2demo.di.modules

import com.google.gson.Gson
import com.pavanpathro.dagger2demo.api.APIService
import com.pavanpathro.dagger2demo.api.Constants
import com.pavanpathro.dagger2demo.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class APIModule {

    @ApplicationScope
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @ApplicationScope
    @Provides
    fun provideGsonConverter(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @ApplicationScope
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(Constants.HOST_URL)
                .client(okHttpClient)
                .build()
    }

    @ApplicationScope
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

}