package co.edu.udem.ejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Switch
import co.edu.udem.ejemplo.model.MyDatabase
import co.edu.udem.ejemplo.model.Note
import co.edu.udem.ejemplo.model.NotesDao
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    private lateinit var note: Note
    private lateinit var notesDao: NotesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val database = MyDatabase.getInstance(this)
        notesDao = database.notesDao()

        val noteId = intent.getIntExtra("id", -1);
        if(noteId!=-1){
            note = notesDao.getNote(noteId)
            findViewById<EditText>(R.id.edit_message).setText(note!!.title)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }else if(item.itemId == R.id.save){
            saveNote()
        }
        return super.onOptionsItemSelected(item)

    }

    fun saveNote(){
        if(!this::note.isInitialized){
            note = Note()
        }
        note!!.title = findViewById<EditText>(R.id.edit_message).getText().toString()
        note!!.reminder = findViewById<Switch>(R.id.switch1).isChecked()
        notesDao.insertNote(note!!)
        finish()
    }

    fun saveNote(view: View) {

        saveNote()

    }
}
