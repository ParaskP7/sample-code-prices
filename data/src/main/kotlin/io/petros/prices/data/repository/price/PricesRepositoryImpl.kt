package io.petros.prices.data.repository.price

import io.petros.prices.data.network.websocket.mapper.WebSocketMessageMapper
import io.petros.prices.domain.model.price.Price
import io.petros.prices.domain.repository.price.PricesRepository
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PricesRepositoryImpl @Inject constructor(
    private val webSocketMessageMapper: WebSocketMessageMapper
) : PricesRepository {

    private var pricePublishSubject = PublishSubject.create<Price>()

    override fun getPriceSubscription(): Observable<Price> {
        return pricePublishSubject
    }

    override fun push(message: String) {
        webSocketMessageMapper.toPrice(message)?.let {
            Timber.d("Price message pushed. [Price: $it]")
            pricePublishSubject.onNext(it)
        }
    }

}
