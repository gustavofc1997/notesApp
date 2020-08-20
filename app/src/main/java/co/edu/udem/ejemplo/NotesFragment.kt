package co.edu.udem.ejemplo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.ejemplo.model.MyDatabase
import co.edu.udem.ejemplo.model.Note
import co.edu.udem.ejemplo.model.NotesDao

class NotesFragment : Fragment(), NotesAdapter.OnNoteSelected {

    private lateinit var adapter: NotesAdapter
    private lateinit var notesDao: NotesDao

    override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View? {

       val rootView = inflater.inflate(R.layout.fragment_notes, container, false)

       val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
       adapter = NotesAdapter(requireContext(), this)
       recyclerView.adapter = adapter
       recyclerView.layoutManager = LinearLayoutManager(context)

       val database = MyDatabase.getInstance(requireContext())
       notesDao = database.notesDao()

       showNotes()
       return rootView
   }

    fun showNotes() {
        val notes = notesDao?.getNotes()
        if (notes != null) {
            adapter.setNotes(notes)
        }
    }

    override fun onNoteSelected(note: Note) {
        val intent = Intent(context, EditActivity::class.java)
        intent.putExtra("id", note.mId)
        intent.putExtra("title", note.title)

        startActivityForResult(intent, 2)
    }

}
