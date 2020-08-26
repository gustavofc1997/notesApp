package co.edu.udem.ejemplo.di

import co.edu.udem.ejemplo.data.NetworkClient
import co.edu.udem.ejemplo.data.NetworkClientImpl
import co.edu.udem.ejemplo.data.Service
import co.edu.udem.ejemplo.model.MyDatabase
import co.edu.udem.ejemplo.model.NotesDao
import co.edu.udem.ejemplo.utils.SERVICE_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    single<OkHttpClient> { provideBaseClient() }
    single<Retrofit> { provideBaseRetrofit(get()) }
    single<MyDatabase> { MyDatabase.getInstance(androidContext()) }
    single<Service> { provideNotesService(get()) }
    single<NotesDao>{ providesDao(get()) }
    single<NetworkClient> { NetworkClientImpl() }
}

fun provideBaseClient(
): OkHttpClient {
    val builder = OkHttpClient.Builder()
    return builder.build()
}

fun provideBaseRetrofit(
    baseOkHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(SERVICE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(baseOkHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideNotesService(retrofit: Retrofit): Service {
    return retrofit.create(Service::class.java)
}

fun providesDao(database: MyDatabase): NotesDao {
    return database.notesDao()
}