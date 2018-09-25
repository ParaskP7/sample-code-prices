package io.petros.prices.data.di.dagger

import dagger.Binds
import dagger.Module
import io.petros.prices.data.repository.price.PricesRepositoryImpl
import io.petros.prices.domain.repository.price.PricesRepository

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindPricesRepository(pricesRepository: PricesRepositoryImpl): PricesRepository

}
