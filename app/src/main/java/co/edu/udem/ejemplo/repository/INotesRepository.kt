package co.edu.udem.ejemplo.repository

import androidx.lifecycle.LiveData
import co.edu.udem.ejemplo.model.Note

interface INotesRepository {
    suspend fun getRemoteNotes(): List<Note>
    suspend fun getLocalNotes(status: Boolean): LiveData<List<Note>>
}