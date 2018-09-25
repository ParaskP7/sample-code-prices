package io.petros.prices.presentation.di.dagger.activity

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.petros.prices.presentation.di.dagger.viewmodel.ViewModelFactory
import io.petros.prices.presentation.feature.prices.InstrumentsActivity
import io.petros.prices.presentation.feature.prices.InstrumentsActivitySubModule

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [InstrumentsActivitySubModule::class])
    abstract fun bindsInstrumentsActivity(): InstrumentsActivity

    /* VIEW MODEL */

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
