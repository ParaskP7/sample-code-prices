package io.petros.prices.presentation.di.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.petros.prices.presentation.App

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}
