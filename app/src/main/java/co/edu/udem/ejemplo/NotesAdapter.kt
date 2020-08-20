package co.edu.udem.ejemplo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.ejemplo.model.Note

class NotesAdapter (
    context: Context, val listener:OnNoteSelected
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

   private val inflater: LayoutInflater = LayoutInflater.from(context)
   private var notes = emptyList<Note>() // Cached copy of notes

   override fun getItemCount() = notes.size

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val itemView = inflater.inflate(R.layout.note_row, parent, false)
       return NoteViewHolder(itemView)
   }

   override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val current = notes[position]
       holder.noteItemView.text = current.title

       holder.noteItemView.setOnClickListener(View.OnClickListener {
               view -> listener.onNoteSelected(current) })
   }

   fun setNotes(notes: List<Note>) {
       this.notes = notes
       notifyDataSetChanged()
   }

   inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val noteItemView: TextView = itemView.findViewById(R.id.textView)
   }

    interface OnNoteSelected{
        fun onNoteSelected(noteId:Note)
    }
}
