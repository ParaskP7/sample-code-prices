package io.petros.prices.data.network.websocket.mapper

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.petros.prices.domain.model.instrument.Instrument
import timber.log.Timber
import javax.inject.Inject

class WebSocketMessageMapper @Inject constructor(
    private val gson: Gson
) {

    fun toInstrument(message: String): Instrument? {
        return try {
            gson.fromJson(message, Instrument::class.java)
        } catch (jse: JsonSyntaxException) {
            Timber.w(jse, "Failed to parse web socket instrument message. [Instrument Message: $message]")
            null
        }
    }

}
