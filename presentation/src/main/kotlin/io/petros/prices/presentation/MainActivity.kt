package io.petros.prices.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.petros.prices.R
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class MainActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)
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
