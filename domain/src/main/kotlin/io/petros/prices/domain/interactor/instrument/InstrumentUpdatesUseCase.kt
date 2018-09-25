package io.petros.prices.domain.interactor.instrument

import io.petros.prices.domain.interactor.UseCase
import io.petros.prices.domain.model.instrument.Instrument
import io.petros.prices.domain.reactive.rx.RxSchedulers
import io.petros.prices.domain.repository.instrument.InstrumentsRepository
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class InstrumentUpdatesUseCase @Inject constructor(
    instrumentsRepository: InstrumentsRepository,
    private val rxSchedulers: RxSchedulers
) : UseCase() {

    private var publishSubject = PublishSubject.create<Instrument>()

    init {
        instrumentsRepository.getInstrumentsSubscription().subscribe(publishSubject)
    }

    fun subscribe(observer: DisposableObserver<Instrument>) {
        publishSubject
            .retry()
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.androidMainThread)
            .subscribe(observer)
        initDisposable(observer)
    }

}
