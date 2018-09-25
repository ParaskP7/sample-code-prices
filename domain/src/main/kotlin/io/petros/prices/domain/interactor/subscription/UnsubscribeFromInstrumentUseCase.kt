package io.petros.prices.domain.interactor.subscription

import io.petros.prices.domain.interactor.UseCaseSingle
import io.petros.prices.domain.reactive.rx.RxSchedulers
import io.petros.prices.domain.repository.subscription.SubscriptionRepository
import io.reactivex.Single
import javax.inject.Inject

class UnsubscribeFromInstrumentUseCase @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository,
    rxSchedulers: RxSchedulers
) : UseCaseSingle<String, UnsubscribeFromInstrumentUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: UnsubscribeFromInstrumentUseCase.Params): Single<String> {
        return subscriptionRepository.unsubscribeFromInstrument(params.isin)
    }

    data class Params constructor(val isin: String) {

        companion object {

            fun with(isin: String): Params {
                return Params(isin)
            }

        }

    }

}
