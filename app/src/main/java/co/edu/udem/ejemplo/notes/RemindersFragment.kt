package co.edu.udem.ejemplo.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.ejemplo.R
import co.edu.udem.ejemplo.adapters.NotesAdapter
import co.edu.udem.ejemplo.edit.EditActivity
import co.edu.udem.ejemplo.model.Note
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RemindersFragment : Fragment(), NotesAdapter.OnNoteSelected {

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
        viewModel.getLocalNotes(true).observe(viewLifecycleOwner,
            Observer<List<Note>> { notes ->
                val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val filtered = notes.sortedBy {
                    LocalDate.parse(it.reminderDate, dateTimeFormatter)
                }
                CoroutineScope(Dispatchers.Main).launch {
                    showNotes(filtered)
                }
            })
    }


    private fun showNotes(notes: List<Note>) {
        adapter.setNotes(notes)
        if (notes.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
        } else {
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