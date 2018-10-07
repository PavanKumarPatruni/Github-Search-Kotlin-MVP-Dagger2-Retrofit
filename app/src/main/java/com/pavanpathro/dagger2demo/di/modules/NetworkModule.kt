package com.pavanpathro.dagger2demo.di.modules

import android.content.Context
import com.pavanpathro.dagger2demo.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

@Module(includes = [ContextModule::class])
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideFile(context: Context): File {
        val cacheFile = File(context.cacheDir, "dagger2demo.cache")
        cacheFile.mkdirs()

        return cacheFile
    }

    @ApplicationScope
    @Provides
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10MB for network cache
    }

    @ApplicationScope
    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.d(it) }).setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

}