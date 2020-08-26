package co.edu.udem.ejemplo.data

import co.edu.udem.ejemplo.model.Note

data class ApiNote(val title:String)

object APIComicMapper {
    fun map(note: ApiNote): Note {
        return Note(
            note.title
        )
    }
}