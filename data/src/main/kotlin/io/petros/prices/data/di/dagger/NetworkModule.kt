package io.petros.prices.data.di.dagger

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.petros.prices.data.R
import io.petros.prices.data.network.WebService
import io.petros.prices.data.network.websocket.WebSocketClient
import io.petros.prices.data.network.websocket.listener.InstrumentsWebSocketListener
import io.petros.prices.data.network.websocket.mapper.WebSocketMessageMapper
import io.petros.prices.domain.repository.price.PricesRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesGson() = Gson()

    @Provides
    @Singleton
    fun providesWebSocketMessageMapper(gson: Gson): WebSocketMessageMapper = WebSocketMessageMapper(gson)

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideWebSocket(
        context: Context,
        httpClient: OkHttpClient,
        pricesRepository: PricesRepository
    ): WebSocket {
        val request = Request.Builder()
            .url(context.getString(R.string.websocket_traderepublic_url))
            .build()
        val listener = InstrumentsWebSocketListener(pricesRepository)
        return httpClient.newWebSocket(request, listener)
    }

    @Provides
    @Singleton
    fun provideWebSocketClient(webSocket: WebSocket): WebSocketClient = WebSocketClient(webSocket)

    @Provides
    @Singleton
    fun provideWebService(webSocketClient: WebSocketClient): WebService = webSocketClient

}
