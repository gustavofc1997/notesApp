package co.edu.udem.ejemplo.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import co.edu.udem.ejemplo.R
import co.edu.udem.ejemplo.edit.EditActivity
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setupNavigation()
        fabBtn.setOnClickListener { createNote() }
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.myNavHostFragment)
        bottom_nav.setupWithNavController(navController)
    }

    private fun createNote() {
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
    }
}
