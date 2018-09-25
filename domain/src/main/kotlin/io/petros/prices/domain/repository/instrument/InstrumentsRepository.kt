package io.petros.prices.domain.repository.instrument

import io.petros.prices.domain.model.instrument.Instrument
import io.reactivex.Observable

interface InstrumentsRepository {

    fun getInstrumentsSubscription(): Observable<Instrument>

    fun push(message: String)

}
