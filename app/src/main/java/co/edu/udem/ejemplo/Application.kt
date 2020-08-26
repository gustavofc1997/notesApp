package co.edu.udem.ejemplo

import android.app.Application
import co.edu.udem.ejemplo.di.repositoryModule
import co.edu.udem.ejemplo.di.serviceModule
import co.edu.udem.ejemplo.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // declare used Android context
            androidContext(this@Application)
            // declare modules
            modules(serviceModule, repositoryModule,viewModelsModule)
        }
    }
}