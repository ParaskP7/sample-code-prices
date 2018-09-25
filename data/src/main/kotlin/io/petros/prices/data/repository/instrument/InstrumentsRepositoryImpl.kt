package io.petros.prices.data.repository.instrument

import io.petros.prices.data.network.websocket.mapper.WebSocketMessageMapper
import io.petros.prices.domain.model.instrument.Instrument
import io.petros.prices.domain.repository.instrument.InstrumentsRepository
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstrumentsRepositoryImpl @Inject constructor(
    private val webSocketMessageMapper: WebSocketMessageMapper
) : InstrumentsRepository {

    private var instrumentsPublishSubject = PublishSubject.create<Instrument>()

    override fun getInstrumentsSubscription(): Observable<Instrument> {
        return instrumentsPublishSubject
    }

    override fun push(message: String) {
        webSocketMessageMapper.toInstrument(message)?.let {
            Timber.d("Instrument message pushed. [Instrument: $it]")
            instrumentsPublishSubject.onNext(it)
        }
    }

}
