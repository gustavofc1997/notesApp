package co.edu.udem.ejemplo.di

import co.edu.udem.ejemplo.notes.NotesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel<NotesViewModel> { NotesViewModel(get()) }
}