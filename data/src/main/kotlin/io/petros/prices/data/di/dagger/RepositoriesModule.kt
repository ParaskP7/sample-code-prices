package io.petros.prices.data.di.dagger

import dagger.Binds
import dagger.Module
import io.petros.prices.data.repository.instrument.InstrumentsRepositoryImpl
import io.petros.prices.data.repository.subscription.SubscriptionRepositoryImpl
import io.petros.prices.domain.repository.instrument.InstrumentsRepository
import io.petros.prices.domain.repository.subscription.SubscriptionRepository

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindInstrumentsRepository(instrumentsRepository: InstrumentsRepositoryImpl): InstrumentsRepository

    @Binds
    abstract fun bindSubscriptionRepository(subscriptionRepository: SubscriptionRepositoryImpl): SubscriptionRepository

}
