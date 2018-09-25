package io.petros.prices.presentation.feature.prices

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.petros.prices.domain.interactor.instrument.InstrumentUpdatesUseCase
import io.petros.prices.domain.interactor.subscription.SubscribeToInstrumentUseCase
import io.petros.prices.domain.interactor.subscription.UnsubscribeFromInstrumentUseCase
import io.petros.prices.domain.model.instrument.Instrument
import io.petros.prices.presentation.feature.prices.subscriber.InstrumentSubscriber
import io.petros.prices.presentation.feature.prices.subscriber.SubscribeToInstrumentSubscriber
import io.petros.prices.presentation.feature.prices.subscriber.UnsubscribeFromInstrumentSubscriber
import javax.inject.Inject

class InstrumentsActivityViewModel @Inject constructor(
    private val instrumentUpdatesUseCase: InstrumentUpdatesUseCase,
    private val subscribeToInstrumentUseCase: SubscribeToInstrumentUseCase,
    private val unsubscribeFromInstrumentUseCase: UnsubscribeFromInstrumentUseCase
) : ViewModel() {

    val instrumentObservable = MutableLiveData<Instrument>()

    fun subscribeForInstrumentUpdates() {
        instrumentUpdatesUseCase.subscribe(InstrumentSubscriber(instrumentObservable))
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
        instrumentUpdatesUseCase.dispose()
        subscribeToInstrumentUseCase.dispose()
        unsubscribeFromInstrumentUseCase.dispose()
    }

}
