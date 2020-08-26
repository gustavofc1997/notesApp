package co.edu.udem.ejemplo

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.edu.udem.ejemplo.model.MyDatabase
import co.edu.udem.ejemplo.model.Note
import co.edu.udem.ejemplo.model.NotesDao
import kotlinx.android.synthetic.main.activity_edit.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EditActivity : AppCompatActivity() {

    private lateinit var notesDao: NotesDao
    private var editNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnSave.setOnClickListener {
            saveNote()
        }
        val database = MyDatabase.getInstance(this)
        notesDao = database.notesDao()

        val noteId = intent.getIntExtra(PARAM_NOTE_ID, -1)
        if (noteId != -1) {
            editNote = notesDao.getNote(noteId)
            editNote?.let { editNote ->
                etDescription.setText(editNote.description)
                edit_message.setText(editNote.title)
                sw_reminder.isChecked = editNote.reminder
                if (sw_reminder.isChecked) {
                    tilDate.visibility = View.VISIBLE
                    etDate.setText(editNote.reminderDate)
                }
            }
        }
        sw_reminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                tilDate.visibility = View.VISIBLE
            } else {
                tilDate.visibility = View.GONE
            }
        }
        etDate.setOnClickListener { showDatePickerDialog() }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadNote(note: Note) {
        note.title = edit_message.text.toString()
        note.description = etDescription.text.toString()
        note.reminder = sw_reminder.isChecked
        val currentDateTime = LocalDateTime.now()
        note.date = currentDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        if (sw_reminder.isChecked)
            note.reminderDate = etDate.text.toString()
    }

    private fun validateViews(): Boolean {
        var status = true
        edit_message.text?.let {
            if (it.toString().isEmpty()) {
                edit_message.error = "Title is required"
                status = false
            }
        }
        etDescription.text?.let {
            if (it.toString().isEmpty()) {
                etDescription.error = "Description is required"
                status = false
            }
        }
        if (sw_reminder.isChecked) {
            etDate.text?.let {
                if (it.toString().isEmpty()) {
                    etDate.error = "Date is required"
                    status = false
                }
            }
        }
        return status
    }

    private fun showDatePickerDialog() {
        val dialog =
            DatePickerDialog(this)
        dialog.datePicker.minDate = Date().time
        dialog.setOnDateSetListener { datePicker, year, month, day ->
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val calendar = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, day)
            }
            etDate.setText(sdf.format(calendar.time))
        }
        dialog.show()
    }

    private fun saveNote() {
        if (!validateViews()) return
        lateinit var noteToSave: Note
        editNote?.let {
            loadNote(it)
            noteToSave = it
        }
        if (editNote == null) {
            noteToSave = Note().also {
                loadNote(it)
            }
        }
        notesDao.insertNote(noteToSave)
        finish()
    }


    companion object {
        const val PARAM_NOTE_ID = "id"
        fun newInstance(context: Context, noteId: Int): Intent {
            return Intent(context, EditActivity::class.java).apply {
                this.putExtra(PARAM_NOTE_ID, noteId)
            }
        }
    }
}
