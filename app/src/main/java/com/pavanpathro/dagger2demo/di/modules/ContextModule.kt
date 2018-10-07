package com.pavanpathro.dagger2demo.di.modules

import android.content.Context
import com.pavanpathro.dagger2demo.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @ApplicationScope
    @Provides fun provideContext(): Context { return context }

}