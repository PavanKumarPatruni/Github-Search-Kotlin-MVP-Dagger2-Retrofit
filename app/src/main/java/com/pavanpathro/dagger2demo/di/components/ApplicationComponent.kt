package com.pavanpathro.dagger2demo.di.components

import com.pavanpathro.dagger2demo.api.APIService
import com.pavanpathro.dagger2demo.di.modules.APIModule
import com.pavanpathro.dagger2demo.di.modules.ContextModule
import com.pavanpathro.dagger2demo.di.modules.NetworkModule
import com.pavanpathro.dagger2demo.di.modules.PicassoModule
import com.pavanpathro.dagger2demo.di.scopes.ApplicationScope
import com.squareup.picasso.Picasso
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(ContextModule::class, NetworkModule::class, APIModule::class, PicassoModule::class))
interface ApplicationComponent {

    fun getPicasso(): Picasso

    fun getAPIService(): APIService

}