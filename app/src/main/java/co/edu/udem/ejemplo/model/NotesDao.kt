package co.edu.udem.ejemplo.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes WHERE reminder =:status")
    fun getNotes(status: Boolean): LiveData<List<Note>>

    @Query("SELECT * FROM notes where _id = :noteId")
    fun getNote(noteId: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()
}
