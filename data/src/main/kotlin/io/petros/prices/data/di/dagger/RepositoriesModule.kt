package io.petros.prices.data.di.dagger

import dagger.Binds
import dagger.Module
import io.petros.prices.data.repository.price.PricesRepositoryImpl
import io.petros.prices.data.repository.subscription.SubscriptionRepositoryImpl
import io.petros.prices.domain.repository.price.PricesRepository
import io.petros.prices.domain.repository.subscription.SubscriptionRepository

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindPricesRepository(pricesRepository: PricesRepositoryImpl): PricesRepository

    @Binds
    abstract fun bindSubscriptionRepository(subscriptionRepository: SubscriptionRepositoryImpl): SubscriptionRepository

}
