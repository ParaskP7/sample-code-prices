package io.petros.prices.data.network.websocket

import com.google.gson.JsonObject
import io.petros.prices.data.network.WebService
import io.reactivex.Single
import okhttp3.WebSocket

class WebSocketClient(
    private val webSocket: WebSocket
) : WebService {

    companion object {

        private const val JSON_SUBSCRIBE_PROPERTY = "subscribe"
        private const val JSON_UNSUBSCRIBE_PROPERTY = "unsubscribe"

    }

    override fun subscribeToInstrument(isin: String): Single<String> {
        val json = JsonObject()
        json.addProperty(JSON_SUBSCRIBE_PROPERTY, isin)
        val success = webSocket.send(json.toString())
        return if (success) Single.just(isin) else Single.error(Exception("Web Socket Send Failure: [ISIN: $isin]"))
    }

    override fun unsubscribeFromInstrument(isin: String): Single<String> {
        val json = JsonObject()
        json.addProperty(JSON_UNSUBSCRIBE_PROPERTY, isin)
        val success = webSocket.send(json.toString())
        return if (success) Single.just(isin) else Single.error(Exception("Web Socket Send Failure: [ISIN: $isin]"))
    }

}
