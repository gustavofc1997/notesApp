package co.edu.udem.ejemplo.model

import androidx.room.*

@Dao
interface NotesDao {
   @Query("SELECT * FROM notes ")
   abstract fun getNotes(): List<Note>

   @Query("SELECT * FROM notes where _id = :noteId")
   abstract fun getNote(noteId:Int): Note

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   abstract fun insertNote(note: Note)

   @Delete
    fun delete(note: Note)

    @Query("Delete from notes")
    fun deleteAllNotes()
}
