package co.edu.udem.ejemplo.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("0c388175-0847-41da-9c18-51b380215fec")
    fun getNotesAsync(): Deferred<Response<List<ApiNote>>>
}