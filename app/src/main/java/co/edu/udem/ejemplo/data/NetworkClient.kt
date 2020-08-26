package co.edu.udem.ejemplo.data

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NetworkClient {
    suspend fun <T : Any> apiCall(call: Deferred<Response<T>>): Response<T>
}