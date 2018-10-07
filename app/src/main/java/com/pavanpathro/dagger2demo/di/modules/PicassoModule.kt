package com.pavanpathro.dagger2demo.di.modules

import android.content.Context
import com.pavanpathro.dagger2demo.di.scopes.ApplicationScope
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module(includes = [ContextModule::class, NetworkModule::class])
class PicassoModule {

    @ApplicationScope
    @Provides
    fun provideOkHttpDownloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

    @ApplicationScope
    @Provides
    fun providePicasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build()
    }


}