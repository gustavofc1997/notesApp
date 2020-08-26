package co.edu.udem.ejemplo.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.edu.udem.ejemplo.model.Note
import co.edu.udem.ejemplo.repository.INotesRepository

class NotesViewModel(private val repository: INotesRepository) : ViewModel() {

    suspend fun getLocalNotes(status: Boolean): LiveData<List<Note>> {
        return repository.getLocalNotes(status)
    }

    suspend fun getRemotes(): List<Note> {
        return repository.getRemoteNotes()
    }
}