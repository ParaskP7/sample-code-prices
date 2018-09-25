package io.petros.prices.presentation.feature.prices.listener

interface SubscriptionCallback {

    fun onSubscribe(isin: String)

    fun onUnsubscribe(isin: String)

}
