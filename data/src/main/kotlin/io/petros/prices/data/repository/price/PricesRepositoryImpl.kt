package io.petros.prices.data.repository.price

import io.petros.prices.domain.model.price.Price
import io.petros.prices.domain.repository.price.PricesRepository
import io.reactivex.Single
import javax.inject.Inject

class PricesRepositoryImpl @Inject constructor() : PricesRepository {

    override fun loadPrices(): Single<Price> {
        return Single.just(Price("DE000BASF111"))
    }

}
