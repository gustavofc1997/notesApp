package co.edu.udem.ejemplo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.ejemplo.model.Note

class NotesAdapter(
    private val listener: OnNoteSelected
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var notes = emptyList<Note>()

    override fun getItemCount() = notes.size

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    interface OnNoteSelected {
        fun onNoteSelected(note: Note)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindViewHolder).bind(notes[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewholder(parent)
    }
}
