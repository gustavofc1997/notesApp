package co.edu.udem.ejemplo.data

import co.edu.udem.ejemplo.repository.NotesNotFoundException
import kotlinx.coroutines.Deferred
import retrofit2.Response

class NetworkClientImpl : NetworkClient {

    override suspend fun <T : Any> apiCall(call: Deferred<Response<T>>): Response<T> {
        val response = call.await()

        if (response.isSuccessful) {
            return response
        }
        throw NotesNotFoundException()
    }
}