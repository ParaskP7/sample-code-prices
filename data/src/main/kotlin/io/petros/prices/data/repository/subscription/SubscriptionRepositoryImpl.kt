package io.petros.prices.data.repository.subscription

import io.petros.prices.data.network.WebService
import io.petros.prices.domain.repository.subscription.SubscriptionRepository
import io.reactivex.Single
import javax.inject.Inject

class SubscriptionRepositoryImpl @Inject constructor(
    private val webService: WebService
) : SubscriptionRepository {

    override fun subscribeToInstrument(isin: String): Single<String> {
        return webService.subscribeToInstrument(isin)
    }

    override fun unsubscribeFromInstrument(isin: String): Single<String> {
        return webService.unsubscribeFromInstrument(isin)
    }

}
