package io.petros.prices.domain.repository.price

import io.petros.prices.domain.model.price.Price
import io.reactivex.Single

interface PricesRepository {

    fun loadPrices(): Single<Price>

}
