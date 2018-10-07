package com.pavanpathro.dagger2demo.application

import android.app.Application
import com.pavanpathro.dagger2demo.api.APIService
import com.pavanpathro.dagger2demo.di.components.DaggerApplicationComponent
import com.pavanpathro.dagger2demo.di.modules.ContextModule
import com.squareup.picasso.Picasso
import timber.log.Timber

class GithubApplication : Application() {

    companion object {
        lateinit var service: APIService

        lateinit var picasso: Picasso
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        val applicationContext = DaggerApplicationComponent.builder().contextModule(ContextModule(this)).build()

        service = applicationContext.getAPIService()

        picasso = applicationContext.getPicasso()
    }

}