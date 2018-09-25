package io.petros.prices.presentation.feature.prices

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.petros.prices.domain.interactor.price.PriceUpdatesUseCase
import io.petros.prices.domain.interactor.subscription.SubscribeToInstrumentUseCase
import io.petros.prices.domain.interactor.subscription.UnsubscribeFromInstrumentUseCase
import io.petros.prices.domain.model.price.Price
import io.petros.prices.presentation.feature.prices.subscriber.PricesSubscriber
import io.petros.prices.presentation.feature.prices.subscriber.SubscribeToInstrumentSubscriber
import io.petros.prices.presentation.feature.prices.subscriber.UnsubscribeFromInstrumentSubscriber
import javax.inject.Inject

class PricesActivityViewModel @Inject constructor(
    private val priceUpdatesUseCase: PriceUpdatesUseCase,
    private val subscribeToInstrumentUseCase: SubscribeToInstrumentUseCase,
    private val unsubscribeFromInstrumentUseCase: UnsubscribeFromInstrumentUseCase
) : ViewModel() {

    val pricesObservable = MutableLiveData<Price>()

    fun subscribeForPriceUpdates() {
        priceUpdatesUseCase.subscribe(PricesSubscriber(pricesObservable))
    }

    fun subscribeToInstrument(isin: String) {
        subscribeToInstrumentUseCase.execute(
            SubscribeToInstrumentSubscriber(),
            SubscribeToInstrumentUseCase.Params.with(isin)
        )
    }

    fun unsubscribeFromInstrument(isin: String) {
        unsubscribeFromInstrumentUseCase.execute(
            UnsubscribeFromInstrumentSubscriber(),
            UnsubscribeFromInstrumentUseCase.Params.with(isin)
        )
    }

    override fun onCleared() {
        super.onCleared()
        priceUpdatesUseCase.dispose()
        subscribeToInstrumentUseCase.dispose()
        unsubscribeFromInstrumentUseCase.dispose()
    }

}
