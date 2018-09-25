package io.petros.prices.data.network

import io.reactivex.Single

interface WebService {

    fun subscribeToInstrument(isin: String): Single<String>

    fun unsubscribeFromInstrument(isin: String): Single<String>

}
