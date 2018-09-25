package io.petros.prices.data.di.dagger

import dagger.Module
import dagger.Provides
import io.petros.prices.data.reactive.rx.RxSchedulersProvider
import io.petros.prices.domain.reactive.rx.RxSchedulers

@Module
class SchedulersModule {

    @Provides
    fun provideRxSchedulers(): RxSchedulers = RxSchedulersProvider.rxSchedulers()

}
