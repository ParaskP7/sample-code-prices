package io.petros.prices.domain.interactor.price

import io.petros.prices.domain.interactor.UseCaseSingle
import io.petros.prices.domain.model.price.Price
import io.petros.prices.domain.reactive.rx.RxSchedulers
import io.petros.prices.domain.repository.price.PricesRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadPricesUseCase @Inject constructor(
    private val reviewsRepository: PricesRepository,
    rxSchedulers: RxSchedulers
) : UseCaseSingle<Price, Unit>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Unit): Single<Price> {
        return reviewsRepository.loadPrices()
    }

}
