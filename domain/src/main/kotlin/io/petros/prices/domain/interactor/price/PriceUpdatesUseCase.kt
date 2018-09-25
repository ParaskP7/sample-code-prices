package io.petros.prices.domain.interactor.price

import io.petros.prices.domain.interactor.UseCase
import io.petros.prices.domain.model.price.Price
import io.petros.prices.domain.reactive.rx.RxSchedulers
import io.petros.prices.domain.repository.price.PricesRepository
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class PriceUpdatesUseCase @Inject constructor(
    pricesRepository: PricesRepository,
    private val rxSchedulers: RxSchedulers
) : UseCase() {

    private var publishSubject = PublishSubject.create<Price>()

    init {
        pricesRepository.getPriceSubscription().subscribe(publishSubject)
    }

    fun subscribe(observer: DisposableObserver<Price>) {
        publishSubject
            .retry()
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.androidMainThread)
            .subscribe(observer)
        initDisposable(observer)
    }

}
