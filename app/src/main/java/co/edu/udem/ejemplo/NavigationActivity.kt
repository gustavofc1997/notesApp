package co.edu.udem.ejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        fabBtn.setOnClickListener { view -> createNote() }

        val navController = findNavController(R.id.myNavHostFragment)

        bottom_nav.setupWithNavController(navController)
    }

    private fun createNote() {
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        if (navHostFragment != null) {
            for (fragment in navHostFragment.getChildFragmentManager().fragments) {
                if (fragment is NotesFragment) {
                    fragment.showNotes()
                } else if (fragment is RemindersFragment) {

                }
            }
        }
    }
}
