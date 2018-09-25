package io.petros.prices.presentation.di.dagger.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.petros.prices.presentation.feature.prices.PricesActivity
import io.petros.prices.presentation.feature.prices.PricesActivitySubModule

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [PricesActivitySubModule::class])
    abstract fun bindsPricesActivity(): PricesActivity

}
