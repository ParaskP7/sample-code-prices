package io.petros.prices.presentation.feature.prices.subscriber

import android.arch.lifecycle.MutableLiveData
import io.petros.prices.domain.model.price.Price
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class PricesSubscriber(
    private val pricesObservable: MutableLiveData<Price>
) : DisposableSingleObserver<Price>() {

    override fun onSuccess(prices: Price) {
        Timber.d("Load prices success. [Price: $prices]")
        pricesObservable.postValue(prices)
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Load prices error.")
    }

}
