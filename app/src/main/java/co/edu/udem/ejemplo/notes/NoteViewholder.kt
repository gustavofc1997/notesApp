package co.edu.udem.ejemplo.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.ejemplo.R
import co.edu.udem.ejemplo.adapters.NotesAdapter
import co.edu.udem.ejemplo.model.Note

class NoteViewholder(itemView: View) : RecyclerView.ViewHolder(itemView), BindViewHolder {
    override fun bind(note: Note, listener: NotesAdapter.OnNoteSelected) {
        setupView(note, listener)
    }

    constructor(parent: ViewGroup)
            : this(LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false))

    private val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
    private val tvNote = itemView.findViewById<TextView>(R.id.tvNote)
    private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    private val tvReminderDate = itemView.findViewById<TextView>(R.id.reminderDate)

    private fun setupView(note: Note, listener: NotesAdapter.OnNoteSelected) {
        tvNote.text = note.title
        if (note.date?.isNotEmpty()!!)
            tvDate.text = "Created on ${note.date}"
        tvDescription.text = note.description
        itemView.setOnClickListener { listener.onNoteSelected(note) }
        tvReminderDate.visibility = View.GONE
        note.reminderDate?.let {
            if (note.reminder) {
                tvReminderDate.text = note.reminderDate
                tvReminderDate.visibility = View.VISIBLE
            }
        }
    }
}

interface BindViewHolder {
    fun bind(
        note: Note,
        listener: NotesAdapter.OnNoteSelected
    )
}