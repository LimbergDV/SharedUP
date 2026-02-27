package com.limbergdv.sharedup.core.ws

import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

data class PostWsEvent(
    val id: Int,
    val title: String,
    val text: String,
    val likeCount: Int,
    val dislikeCount: Int,
    val idUser: Int
)

@Singleton
class PostWebSocketManager @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    private var webSocket: WebSocket? = null

    companion object {
        private const val WS_URL = "wss://sharedupapi.duckdns.org/ws/posts"
        private const val TAG = "PostWebSocket"
    }

    fun observePosts(): Flow<PostWsEvent> = callbackFlow {

        val request = Request.Builder()
            .url(WS_URL)
            .build()


        var isProcessing = false

        val listener = object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d(TAG, "Conectado al WebSocket")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(TAG, "JSON CRUDO: $text")

                synchronized(this) {
                    if (isProcessing) {
                        Log.d(TAG, "Mensaje duplicado de thread paralelo, ignorado")
                        return
                    }
                    isProcessing = true
                }

                try {
                    val json = JSONObject(text)
                    val data = json.getJSONObject("data")

                    val event = PostWsEvent(
                        id           = data.optInt("id", 0),
                        title        = data.optString("title", "").trim(),
                        text         = data.optString("text", "").trim(),
                        likeCount    = data.optInt("like_count", 0),
                        dislikeCount = data.optInt("dislike_count", 0),
                        idUser       = data.optInt("iduser", 0)
                    )

                    Log.d(TAG, "Post parseado — título: '${event.title}', usuario: ${event.idUser}")
                    trySend(event)

                } catch (e: Exception) {
                    Log.e(TAG, "Error al parsear: ${e.message}")
                } finally {
                    synchronized(this) { isProcessing = false }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e(TAG, " Error en WebSocket: ${t.message}")
                close(t)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d(TAG, " WebSocket cerrado: $reason")
                close()
            }
        }

        webSocket = okHttpClient.newWebSocket(request, listener)

        awaitClose {
            Log.d(TAG, "Cerrando WebSocket")
            webSocket?.close(1000, "Pantalla cerrada")
            webSocket = null
        }
    }

    fun disconnect() {
        webSocket?.close(1000, "Desconexión manual")
        webSocket = null
    }
}