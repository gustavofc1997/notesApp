package co.edu.udem.ejemplo.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.ejemplo.EditActivity
import co.edu.udem.ejemplo.R
import co.edu.udem.ejemplo.model.MyDatabase
import co.edu.udem.ejemplo.model.Note
import co.edu.udem.ejemplo.model.NotesDao
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.coroutines.*

class NotesFragment : Fragment(), NotesAdapter.OnNoteSelected {

    private lateinit var adapter: NotesAdapter
    private lateinit var notesDao: NotesDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_notes, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        adapter = NotesAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val database = MyDatabase.getInstance(requireContext())
        notesDao = database.notesDao()
        GlobalScope.launch {
            observeDb()
        }
        return rootView
    }

    private suspend fun observeDb() = withContext(Dispatchers.Main) {
        notesDao.getNotes(status = false).observe(viewLifecycleOwner,
            Observer<List<Note>> { notes -> // update the UI here
                CoroutineScope(Dispatchers.Main).launch {
                    showNotes(notes)
                }
            })
    }

    private fun showNotes(notes: List<Note>) {
        adapter.setNotes(notes)
        if (notes.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
        }else{
            tvEmpty.visibility = View.GONE
        }
    }

    override fun onNoteSelected(note: Note) {
        note.mId?.let { id ->
            val intent = context?.let { EditActivity.newInstance(it, id) }
            startActivity(intent)
        }
    }

}
