package io.petros.prices.data.network.websocket.listener

import io.petros.prices.domain.repository.instrument.InstrumentsRepository
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import timber.log.Timber

class InstrumentsWebSocketListener (
    private val instrumentsRepository: InstrumentsRepository
) : WebSocketListener() {

    companion object {

        private const val NORMAL_CLOSE_STATUS_CODE = 1000

    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Timber.d("Web socket opened: [Response Message: ${response.message()}]")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Timber.v("Web socket message received: [Text: $text]")
        instrumentsRepository.push(text)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Timber.v("Web socket message received [Bytes HEX: ${bytes.hex()}]")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(NORMAL_CLOSE_STATUS_CODE, null)
        Timber.d("Web socket closing: [Code: $code, Reason: $reason]")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Timber.d("Web socket closed: [Code: $code, Reason: $reason]")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Timber.e("Web socket failure: [Exception: ${t.message}, Response Message: ${response?.message()}]")
    }

}
