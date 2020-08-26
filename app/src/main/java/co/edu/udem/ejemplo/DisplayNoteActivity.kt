package co.edu.udem.ejemplo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_display_note.*

class DisplayNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_note)
        initViews()
    }

    private fun initViews() {
        val message = intent.getStringExtra(NOTA)
        textView.text = message
    }

    companion object {
        const val NOTA = "PARAM_NOTA"
        fun newInstance(context: Context, value: String): Intent {
            return Intent(context, DisplayNoteActivity::class.java).apply {
                putExtra(NOTA, value)
            }
        }
    }
}
