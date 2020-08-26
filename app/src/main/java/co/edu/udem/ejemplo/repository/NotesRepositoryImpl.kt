package co.edu.udem.ejemplo.repository

import androidx.lifecycle.LiveData
import co.edu.udem.ejemplo.data.APIComicMapper
import co.edu.udem.ejemplo.data.NetworkClient
import co.edu.udem.ejemplo.data.Service
import co.edu.udem.ejemplo.model.Note
import co.edu.udem.ejemplo.model.NotesDao

class NotesNotFoundException : Throwable()

class NotesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val notesService: Service,
    private val notesDao: NotesDao
) : INotesRepository {

    override suspend fun getRemoteNotes(): List<Note> {
        val response = networkClient.apiCall(notesService.getNotesAsync()).body()?.let {
            it
        } ?: throw NotesNotFoundException()
        return response.map {
            APIComicMapper.map(it)
        }
    }

    override suspend fun getLocalNotes(status: Boolean): LiveData<List<Note>> {
        return notesDao.getNotes(status)
    }
}