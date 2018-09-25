package io.petros.prices.presentation.feature.prices.subscriber

import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class UnsubscribeFromInstrumentSubscriber : DisposableSingleObserver<String>() {

    override fun onSuccess(isin: String) {
        Timber.d("Unsubscribe from instrument success. [ISIN: $isin]")
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Unsubscribe from instrument error.")
    }

}
