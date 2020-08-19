package co.edu.udem.ejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveNote(view: View) {

        val intent = Intent(this, DisplayNoteActivity::class.java)

//        val editText = findViewById<EditText>(R.id.edit_message)

        val message = edit_message.text.toString()

        intent.putExtra("nota1", message)

        startActivity(intent);

    }
}
