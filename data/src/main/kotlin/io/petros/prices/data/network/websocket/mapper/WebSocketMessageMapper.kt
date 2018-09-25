package io.petros.prices.data.network.websocket.mapper

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.petros.prices.domain.model.price.Price
import timber.log.Timber
import javax.inject.Inject

class WebSocketMessageMapper @Inject constructor(
    private val gson: Gson
) {

    fun toPrice(message: String): Price? {
        return try {
            gson.fromJson(message, Price::class.java)
        } catch (jse: JsonSyntaxException) {
            Timber.w(jse, "Failed to parse web socket price message. [Price Message: $message]")
            null
        }
    }

}
