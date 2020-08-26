package co.edu.udem.ejemplo.di

import co.edu.udem.ejemplo.repository.INotesRepository
import co.edu.udem.ejemplo.repository.NotesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<INotesRepository> { NotesRepositoryImpl(get(), get(), get()) }
}