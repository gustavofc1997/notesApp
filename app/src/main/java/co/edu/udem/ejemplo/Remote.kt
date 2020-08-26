package co.edu.udem.ejemplo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.ejemplo.adapters.NotesAdapter
import co.edu.udem.ejemplo.edit.EditActivity
import co.edu.udem.ejemplo.model.Note
import co.edu.udem.ejemplo.notes.NotesViewModel
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class Remote : Fragment(), NotesAdapter.OnNoteSelected {
    private lateinit var adapter: NotesAdapter
    private val viewModel by viewModel<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_notes, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        adapter = NotesAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        GlobalScope.launch {
            observeDb()
        }
        return rootView
    }

    private suspend fun observeDb() = withContext(Dispatchers.Main) {
        showNotes(viewModel.getRemotes())
    }


    private fun showNotes(notes: List<Note>) {
        adapter.setNotes(notes)
        if (notes.isEmpty()) {
            tvEmpty?.let {
                it.visibility = View.GONE
            }
        } else {
            tvEmpty?.let {
                it.visibility = View.GONE
            }
        }
    }


    override fun onNoteSelected(note: Note) {
        note.mId?.let { id ->
            val intent = context?.let { EditActivity.newInstance(it, id) }
            startActivity(intent)
        }
    }
}