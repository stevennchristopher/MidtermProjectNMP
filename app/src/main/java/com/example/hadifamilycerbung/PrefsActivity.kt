package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityPrefsBinding

class PrefsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrefsBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityPrefsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            // Sembunyikan default ActionBar jika perlu
            setDisplayShowTitleEnabled(false)
        }
        supportActionBar?.title = "Prefs"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu -> {
                // Tindakan yang ingin dilakukan saat menu diklik
                true
            }
            R.id.item_notification -> {
                // Tindakan yang ingin dilakukan saat menu diklik
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}