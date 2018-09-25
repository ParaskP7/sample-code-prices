package io.petros.prices.domain.repository.price

import io.petros.prices.domain.model.price.Price
import io.reactivex.Observable

interface PricesRepository {

    fun getPriceSubscription(): Observable<Price>

    fun push(message: String)

}
