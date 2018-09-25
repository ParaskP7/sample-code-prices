package io.petros.prices.presentation.feature.prices

import android.annotation.SuppressLint
import android.os.Bundle
import io.petros.prices.R
import io.petros.prices.presentation.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_prices.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class PricesActivity : BaseActivity() {

    companion object {

        private const val NORMAL_CLOSE_STATUS_CODE = 1000
        private const val WEB_SOCKET_RUL = "ws://159.89.15.214:8080"

        private val SUBSCRIBE_MESSAGE = """
            {"subscribe": "DE000BASF111"}
        """.trimIndent()
        private val UNSUBSCRIBE_MESSAGE = """
            {"unsubscribe": "DE000BASF111"}
        """.trimIndent()

    }

    private lateinit var client: OkHttpClient
    private lateinit var webSocket: WebSocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWebSocket()
        initButtons()
    }

    private fun initWebSocket() {
        val request = Request.Builder().url(WEB_SOCKET_RUL).build()
        val listener = TestWebSocketListener()
        client = OkHttpClient()
        webSocket = client.newWebSocket(request, listener)
    }

    private fun initButtons() {
        subscribe.setOnClickListener { webSocket.send(SUBSCRIBE_MESSAGE) }
        unsubscribe.setOnClickListener { webSocket.send(UNSUBSCRIBE_MESSAGE) }
    }

    private fun output(text: String) {
        runOnUiThread { output.text = text }
    }

    override fun onDestroy() {
        client.dispatcher().executorService().shutdown()
        super.onDestroy()
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_prices

    /* INNER CLASSES */

    @SuppressLint("SyntheticAccessor")
    private inner class TestWebSocketListener : WebSocketListener() {

        override fun onOpen(webSocket: WebSocket, response: Response) {
            output("Open: ${response.message()}")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Receiving: $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            output("Receiving Bytes: ${bytes.hex()}")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSE_STATUS_CODE, null)
            output("Closing: $code / $reason")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            output("Closed: $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error: ${t.message} / ${response?.message()}")
        }

    }

}
