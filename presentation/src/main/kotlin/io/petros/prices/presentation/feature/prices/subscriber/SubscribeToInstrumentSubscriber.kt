package io.petros.prices.presentation.feature.prices.subscriber

import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class SubscribeToInstrumentSubscriber : DisposableSingleObserver<String>() {

    override fun onSuccess(isin: String) {
        Timber.d("Subscribe to instrument success. [ISIN: $isin]")
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Subscribe to instrument error.")
    }

}
