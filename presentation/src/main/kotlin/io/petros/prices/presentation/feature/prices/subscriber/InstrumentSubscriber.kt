package io.petros.prices.presentation.feature.prices.subscriber

import android.arch.lifecycle.MutableLiveData
import io.petros.prices.domain.model.instrument.Instrument
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class InstrumentSubscriber(
    private val instrumentObservable: MutableLiveData<Instrument>
) : DisposableObserver<Instrument>() {

    override fun onComplete() {
        Timber.v("Instrument update complete.")
    }

    override fun onNext(instrument: Instrument) {
        Timber.d("Instrument update success. [Instrument: $instrument]")
        instrumentObservable.postValue(instrument)
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Instrument update error.")
        instrumentObservable.postValue(null)
    }

}
