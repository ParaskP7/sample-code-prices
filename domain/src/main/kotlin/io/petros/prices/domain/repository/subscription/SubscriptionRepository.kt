package io.petros.prices.domain.repository.subscription

import io.reactivex.Single

interface SubscriptionRepository {

    fun subscribeToInstrument(isin: String): Single<String>

    fun unsubscribeFromInstrument(isin: String): Single<String>

}
