package com.limbergdv.sharedup.core.network

import android.util.Log
import com.limbergdv.sharedup.core.session.TokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// Añadimos @Inject para que Hilt sepa cómo crearlo
class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = runBlocking {
            tokenDataStore.getToken()
        }

        Log.d("NETWORK", "Request URL: ${chain.request().url}")
        Log.d("NETWORK", "Token presente: ${!token.isNullOrBlank()}")

        val request = if (!token.isNullOrBlank()) {
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}