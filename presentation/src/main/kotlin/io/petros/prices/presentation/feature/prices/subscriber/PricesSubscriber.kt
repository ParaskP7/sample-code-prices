package io.petros.prices.presentation.feature.prices.subscriber

import android.arch.lifecycle.MutableLiveData
import io.petros.prices.domain.model.price.Price
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class PricesSubscriber(
    private val pricesObservable: MutableLiveData<Price>
) : DisposableObserver<Price>() {

    override fun onComplete() {
        Timber.v("Load prices complete.")
    }

    override fun onNext(prices: Price) {
        Timber.d("Price update success. [Price: $prices]")
        pricesObservable.postValue(prices)
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Price update error.")
        pricesObservable.postValue(null)
    }

}
